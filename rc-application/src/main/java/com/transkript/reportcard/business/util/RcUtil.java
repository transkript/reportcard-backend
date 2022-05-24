package com.transkript.reportcard.business.util;

import com.trankscript.reportcard.model.RcClassLevel;
import com.trankscript.reportcard.model.RcGrade;
import com.trankscript.reportcard.model.RcStudent;
import com.trankscript.reportcard.model.RcSubject;
import com.trankscript.reportcard.model.ReportCard;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.Term;
import com.transkript.reportcard.data.entity.relation.Grade;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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
        termGrades.forEach((sequence, grades)-> {
            grades.forEach(grade -> {
                RcGrade rcGrade = new RcGrade(
                        grade.getScore(), grade.getDescription(),
                        sequence.getTerm().getAcademicYear().getName(),
                        sequence.getTerm().getName(), sequence.getName()
                );
                Subject subject = grade.getSubjectRegistration().getSubject();
                if(rcSubjectMap.containsKey(subject.getId())) {
                    rcSubjectMap.get(subject.getId()).getRcGrades().add(rcGrade);
                } else {
                    RcSubject rcSubject = new RcSubject(subject.getName(), subject.getCoefficient(), subject.getCode(), new ArrayList<>());
                    rcSubject.getRcGrades().add(rcGrade);
                    rcSubjectMap.put(subject.getId(), rcSubject);
                }
            });
        });

        RcStudent rcStudent = new RcStudent(student.getName(), student.getRegNum(), student.getGender().name(),
                student.getDob(), student.getPob(), rcClassLevel, rcSubjectMap.values().stream().toList()
        );
        ReportCard reportCard = new ReportCard(rcStudent);
        System.out.println(reportCard.getAverage());
    }
}
