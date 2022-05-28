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
                    grade = Grade.builder().score(0F).description("This grade has not been added").sequence(sequence).subjectRegistration(subjectRegistration).build();
                }
                termGrades.get(sequence).add(grade);
            });
        });

        rcUtil.generateReportCard(student, term, classLevelSub, termGrades);
    }
}
