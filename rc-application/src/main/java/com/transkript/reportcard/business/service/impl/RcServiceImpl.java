package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.request.ReportCardRequest;
import com.transkript.reportcard.business.service.GradeService;
import com.transkript.reportcard.business.service.RcService;
import com.transkript.reportcard.business.service.StudentApplicationService;
import com.transkript.reportcard.business.service.TermService;
import com.transkript.reportcard.business.util.RcUtil;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import com.transkript.reportcard.data.entity.Term;
import com.transkript.reportcard.data.entity.composite.ApplicationKey;
import com.transkript.reportcard.data.entity.composite.GradeKey;
import com.transkript.reportcard.data.entity.relation.Grade;
import com.transkript.reportcard.data.enums.GradeDesc;
import com.transkript.reportcard.exception.EntityException;
import com.transkript.reportcard.exception.ReportCardException;
import com.transkript.reportcard.model.ReportCard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class RcServiceImpl implements RcService {
    private final StudentApplicationService studentApplicationService;
    private final TermService termService;
    private final GradeService gradeService;
    RcUtil rcUtil = RcUtil.getInstance();

    @Override
    public File getReportCard(Long termId, ReportCardRequest reportCardRequest) {
        ApplicationKey applicationKey = new ApplicationKey(reportCardRequest.studentId(), reportCardRequest.yearId());
        StudentApplication application = studentApplicationService.getStudentApplicationEntity(applicationKey);
        Student student = application.getStudent();
        ClassLevelSub classLevelSub = application.getClassLevelSub();

        Term term = termService.getTermEntity(termId);

        ReportCard reportCard = getReportCardModel(student, application, term, classLevelSub, reportCardRequest);
        List<ReportCard> classReportCards = getClassReportCardModels(term, classLevelSub, reportCardRequest);

        reportCard = rcUtil.processReportCard(reportCard.getStudentId(), classReportCards);

        return RcUtil.generateReportCard(reportCard);
    }

    private ReportCard getReportCardModel(Student student, StudentApplication application, Term term, ClassLevelSub classLevelSub, ReportCardRequest reportCardRequest) {
        Long openSeqId = reportCardRequest.sequenceRequest().openingSequenceId();
        Long closeSeqId = reportCardRequest.sequenceRequest().closingSequenceId();
        // block to valid opening and closing sequences
        Sequence openSeq, closeSeq;
        {
            openSeq = term.getSequences().stream().filter(seq -> seq.getId().equals(openSeqId)).findFirst().orElse(null);
            closeSeq = term.getSequences().stream().filter(seq -> seq.getId().equals(closeSeqId)).findFirst().orElse(null);
            if (openSeq == null || closeSeq == null) {
                throw new ReportCardException.IllegalStateException("Opening and closing sequence pair does not exist for this term" + term.getId());
            }
        }

        String[] sequenceNames = new String[]{openSeq.getName(), closeSeq.getName()};

        SubjectRegistration[] subjectRegistrations = new SubjectRegistration[application.getSubjectRegistrations().size()];
        Map<Subject, Grade[]> subjectGrades = new HashMap<>();
        application.getSubjectRegistrations().toArray(subjectRegistrations);

        Arrays.stream(subjectRegistrations).forEach(subjectRegistration -> {
            Grade openGrade, closeGrade;
            {
                try {
                    openGrade = gradeService.getGradeEntity(GradeKey.builder().sequenceId(openSeq.getId()).registrationId(subjectRegistration.getId()).build());
                } catch(EntityException.EntityNotFoundException e) {
                    log.info("Opening grade not found for sequence {} and registration {}", openSeq.getId(), subjectRegistration.getId());
                    openGrade = Grade.builder().score(0F).description(GradeDesc.NOT_GRADED).sequence(openSeq).subjectRegistration(subjectRegistration).build();
                }
                try {
                    closeGrade = gradeService.getGradeEntity(GradeKey.builder().sequenceId(closeSeq.getId()).registrationId(subjectRegistration.getId()).build());
                } catch(EntityException.EntityNotFoundException e) {
                    log.info("Closing grade not found for sequence {} and registration {}", closeSeq.getId(), subjectRegistration.getId());
                    closeGrade = Grade.builder().score(0F).description(GradeDesc.NOT_GRADED).sequence(closeSeq).subjectRegistration(subjectRegistration).build();
                }
            }
            subjectGrades.put(subjectRegistration.getSubject(), new Grade[]{openGrade, closeGrade});
        });

        return rcUtil.createReportCard(student, application, term, classLevelSub, subjectGrades, sequenceNames);
    }

    private List<ReportCard> getClassReportCardModels(Term term, ClassLevelSub classLevelSub, ReportCardRequest reportCardRequest) {
        List<StudentApplication> applications = classLevelSub.getStudentApplications().stream()
                .filter((application) -> application.getAcademicYear().getId().equals(reportCardRequest.yearId()))
                .toList();
        List<ReportCard> classReportCards = new ArrayList<>();
        for (StudentApplication application : applications) {
            classReportCards.add(getReportCardModel(application.getStudent(), application, term, classLevelSub, reportCardRequest));
        }

        return classReportCards;
    }

}
