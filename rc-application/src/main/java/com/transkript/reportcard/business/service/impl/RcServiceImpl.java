package com.transkript.reportcard.business.service.impl;

import com.trankscript.reportcard.model.RcClassLevel;
import com.trankscript.reportcard.model.RcGrade;
import com.trankscript.reportcard.model.RcStudent;
import com.trankscript.reportcard.model.RcSubject;
import com.trankscript.reportcard.model.ReportCard;
import com.transkript.reportcard.business.service.GradeService;
import com.transkript.reportcard.business.service.RcService;
import com.transkript.reportcard.business.service.SequenceService;
import com.transkript.reportcard.business.service.StudentApplicationService;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.composite.ApplicationKey;
import com.transkript.reportcard.data.entity.composite.GradeKey;
import com.transkript.reportcard.data.entity.relation.Grade;
import com.transkript.reportcard.exception.EntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RcServiceImpl implements RcService {
    private final StudentApplicationService studentApplicationService;
    private final SequenceService sequenceService;
    private final GradeService gradeService;

    @Override
    public void getReportCard(Long studentId, Long yearId, Long sequenceId) {
        ApplicationKey applicationKey = new ApplicationKey(studentId, yearId);
        StudentApplication application = studentApplicationService.getStudentApplicationEntity(applicationKey);
        Sequence sequence = sequenceService.getSequenceEntity(sequenceId);
        Student student = application.getStudent();
        ClassLevelSub classLevelSub = application.getClassLevelSub();


        List<Grade> gradeList = new ArrayList<>();

        application.getSubjectRegistrations().forEach((subjectRegistration) -> {
            try {
                Grade grade = gradeService.getGradeEntity(new GradeKey(sequenceId, subjectRegistration.getId()));
                gradeList.add(grade);
            } catch (EntityException.EntityNotFoundException e) {
                log.warn("Grade not found for subjectRegistrationId: {} and sequenceId: {}. Skipping...", subjectRegistration.getId(), sequenceId);
            }
        });

        generateReportCard(student, sequence, classLevelSub, gradeList);
    }

    private void generateReportCard(Student student, Sequence sequence, ClassLevelSub classLevelSub, List<Grade> grades) {
        RcClassLevel rcClassLevel = new RcClassLevel(classLevelSub.getClassLevel().getName(), classLevelSub.getName());
        List<RcSubject> rcSubjects = new ArrayList<>();
        grades.forEach((grade) -> {
            RcGrade rcGrade = new RcGrade(grade.getScore(), grade.getDescription(),
                    sequence.getTerm().getAcademicYear().getName(),
                    sequence.getTerm().getName(), sequence.getName()
            );

            Subject subject = grade.getSubjectRegistration().getSubject();
            RcSubject rcSubject = new RcSubject(subject.getName(), subject.getCoefficient(), subject.getCode(), rcGrade);
            rcSubjects.add(rcSubject);
        });

        RcStudent rcStudent = new RcStudent(student.getName(), student.getRegNum(), student.getGender().name(),
                student.getDob(), student.getPob(), rcClassLevel, rcSubjects
        );
        ReportCard reportCard = new ReportCard(rcStudent);
        System.out.println(reportCard.getAverage());
    }
}
