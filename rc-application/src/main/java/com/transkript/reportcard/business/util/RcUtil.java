package com.transkript.reportcard.business.util;

import com.trankscript.reportcard.ReportCardProcess;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.entity.Section;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.Term;
import com.transkript.reportcard.data.entity.relation.Grade;
import com.transkript.reportcard.model.RcClassLevel;
import com.transkript.reportcard.model.RcGrade;
import com.transkript.reportcard.model.RcSchool;
import com.transkript.reportcard.model.RcStudent;
import com.transkript.reportcard.model.RcSubject;
import com.transkript.reportcard.model.ReportCard;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RcUtil {
    @NotNull
    @Contract(value = " -> new", pure = true)
    public static RcUtil getInstance() {
        return new RcUtil();
    }

    public void generateReportCard(@NotNull Student student, @NotNull Term term, @NotNull ClassLevelSub classLevelSub, @NotNull Map<Sequence, List<Grade>> termGrades) {
        RcClassLevel rcClassLevel = new RcClassLevel(classLevelSub.getClassLevel().getName(), classLevelSub.getName(), term.getAcademicYear().getName());
        Map<Long, RcSubject> rcSubjectMap = new HashMap<>();
        List<String> sequenceNames = new ArrayList<>(2);
        termGrades.forEach((sequence, grades) -> {
            sequenceNames.add(sequence.getName());
            grades.forEach(grade -> {
                Subject subject = grade.getSubjectRegistration().getSubject();
                RcGrade rcGrade = new RcGrade(sequence.getName(), grade.getScore());

                if (rcSubjectMap.containsKey(subject.getId())) {
                    rcSubjectMap.get(subject.getId()).getGrades().put(rcGrade.getSequence(), rcGrade);
                } else {
                    RcSubject rcSubject = new RcSubject(subject.getName(), subject.getCoefficient(), subject.getCode(), new HashMap<>());
                    rcSubject.getGrades().put(rcGrade.getSequence(), rcGrade);
                    rcSubjectMap.put(subject.getId(), rcSubject);
                }
            });
        });

        Section section = classLevelSub.getClassLevel().getSection();
        RcSchool rcSchool = new RcSchool(section.getSchool().getName(), section.getName());
        RcStudent rcStudent = new RcStudent(student.getName(), student.getRegNum(), student.getGender().name(),
                student.getDob(), student.getPob(), rcClassLevel, rcSubjectMap.values().stream().toList()
        );
        System.out.println(rcStudent);
        ReportCard reportCard = new ReportCard(rcSchool, rcStudent, term.getName(), sequenceNames);
        System.out.println(reportCard.getAverage());

        try {
            ReportCardProcess.generateReportCard(reportCard);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
