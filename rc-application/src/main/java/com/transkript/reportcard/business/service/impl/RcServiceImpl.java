package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.business.service.GradeService;
import com.transkript.reportcard.business.service.RcService;
import com.transkript.reportcard.business.service.StudentApplicationService;
import com.transkript.reportcard.business.service.TermService;
import com.transkript.reportcard.business.util.RcUtil;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.entity.Term;
import com.transkript.reportcard.data.entity.composite.ApplicationKey;
import com.transkript.reportcard.data.entity.composite.GradeKey;
import com.transkript.reportcard.data.entity.relation.Grade;
import com.transkript.reportcard.exception.EntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void getReportCard(Long studentId, Long yearId, Long termId) {
        ApplicationKey applicationKey = new ApplicationKey(studentId, yearId);
        StudentApplication application = studentApplicationService.getStudentApplicationEntity(applicationKey);
        Term term = termService.getTermEntity(termId);
        List<Sequence> sequences = term.getSequences();

        Student student = application.getStudent();
        ClassLevelSub classLevelSub = application.getClassLevelSub();

        Map<Sequence, List<Grade>> termGrades = new HashMap<>();

        sequences.forEach((sequence) -> {
            termGrades.put(sequence, new ArrayList<>());
            application.getSubjectRegistrations().forEach((subjectRegistration) -> {
                Grade grade;
                try {
                    grade = gradeService.getGradeEntity(GradeKey.builder().sequenceId(sequence.getId()).registrationId(subjectRegistration.getId()).build());
                } catch (EntityException.EntityNotFoundException e) {
                    grade = Grade.builder().score(0F).description("").sequence(sequence).subjectRegistration(subjectRegistration).build();
                }
                termGrades.get(sequence).add(grade);
            });
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
