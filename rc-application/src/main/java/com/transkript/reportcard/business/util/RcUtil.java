package com.transkript.reportcard.business.util;

import com.transkript.reportcard.ReportCardProcess;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.entity.Section;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.Term;
import com.transkript.reportcard.data.entity.relation.Grade;
import com.transkript.reportcard.data.enums.GradeDesc;
import com.transkript.reportcard.exception.ReportCardException;
import com.transkript.reportcard.model.ClassLevelInfo;
import com.transkript.reportcard.model.GradeInfo;
import com.transkript.reportcard.model.ReportCard;
import com.transkript.reportcard.model.SchoolInfo;
import com.transkript.reportcard.model.StudentInfo;
import com.transkript.reportcard.model.SubjectInfo;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class RcUtil {
    @NotNull
    @Contract(value = " -> new", pure = true)
    public static RcUtil getInstance() {
        return new RcUtil();
    }

    public static File generateReportCard(@NotNull ReportCard reportCard) throws ReportCardException {
        try {
            return ReportCardProcess.generateReportCardFile(reportCard);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ReportCard createReportCard(
            @NotNull Student student, @NotNull StudentApplication application, @NotNull Term term,
            @NotNull ClassLevelSub classLevelSub, @NotNull Map<Subject, Grade[]> subjectGrades, String[] sequenceNames) {
        ClassLevelInfo classLevelInfo = new ClassLevelInfo(classLevelSub.getClassLevel().getName(), classLevelSub.getName(), term.getAcademicYear().getName());
        List<SubjectInfo> subjectInfos = new ArrayList<>();

        for (Map.Entry<Subject, Grade[]> entry : subjectGrades.entrySet()) {
            Subject subject = entry.getKey();
            Grade[] grades = entry.getValue();

            if (grades.length != 2) {
                throw new ReportCardException.IllegalStateException("Grade array length must be 2");
            }
            GradeInfo seqAGradeInfo = new GradeInfo(grades[0].getScore(), grades[0].getDescription() != GradeDesc.NOT_GRADED);
            GradeInfo seqBGradeInfo = new GradeInfo(grades[1].getScore(), grades[1].getDescription() != GradeDesc.NOT_GRADED);
            SubjectInfo subjectInfo = new SubjectInfo(subject.getId(), subject.getName(), subject.getCoefficient(), subject.getCode(), seqAGradeInfo, seqBGradeInfo);
            subjectInfos.add(subjectInfo);
        }

        Section section = classLevelSub.getClassLevel().getSection();
        SchoolInfo schoolInfo = new SchoolInfo(section.getSchool().getName(), section.getName(), term.getAcademicYear().getName(), term.getName(), sequenceNames[0], sequenceNames[1]);
        StudentInfo studentInfo = new StudentInfo(student.getName(), student.getRegNum(), student.getGender().name(),
                student.getDob(), student.getPob(), application.getRepeating() == null || application.getRepeating()
        );

        return new ReportCard(student.getId(), schoolInfo, studentInfo, classLevelInfo, subjectInfos);
    }

    public ReportCard processReportCard(Long studentId, List<ReportCard> classReportCards) {
        AtomicReference<ReportCard> card = new AtomicReference<>();
        processReportCardsClassRank(classReportCards);
        classReportCards.forEach((r) -> {
            if (r.getStudentId() == studentId) {
                card.set(r);
            }
        });
        return card.get();
    }

    public void processReportCardsClassRank(List<ReportCard> classReportCards) {
        // sort the report cards by average score
        classReportCards.sort(Comparator.comparingDouble(ReportCard::getAverage));

        // reverse order of cards
        for (int i = 0; i < classReportCards.size() / 2; i++) {
            ReportCard temp = classReportCards.get(i);
            classReportCards.set(i, classReportCards.get(classReportCards.size() - i - 1));
            classReportCards.set(classReportCards.size() - i - 1, temp);
        }

        List<Double> averages = new ArrayList<>();
        classReportCards.forEach(r -> averages.add(r.getAverage()));

        Double averagesTotal = 0D;
        for (Double avg : averages) {
            averagesTotal += avg;
        }
        double classAverage = averagesTotal / averages.size();

        for (int i = 0; i < classReportCards.size(); i++) {

            classReportCards.get(i).setRank(i + 1);
            classReportCards.get(i).setClassAverage(classAverage);
        }

        processReportCardsSubjectRank(classReportCards);
        classReportCards.forEach(card -> {
            System.out.println("\n" + card.getStudentId());
            card.getSubjectInfos().forEach(System.out::println);
        });
    }

    private void processReportCardsSubjectRank(List<ReportCard> classReportCards) {
        Map<Long, List<ReportCard>> subjectRanks = new HashMap<>();
        classReportCards.forEach(card -> card.getSubjectInfos().forEach(subjectInfo -> {
            if (!subjectRanks.containsKey(subjectInfo.getId())) {
                subjectRanks.put(subjectInfo.getId(), new ArrayList<>());
            }
            subjectRanks.get(subjectInfo.getId()).add(card);
        }));


        subjectRanks.forEach((subjectId, cards) -> {
            Map<Long, SubjectInfo> subjectInfosById = new HashMap<>();
            cards.forEach(card -> card.getSubjectInfos().stream()
                    .filter(subjectInfo -> subjectInfo.getId() == subjectId).findFirst()
                    .ifPresent(subjectInfo -> subjectInfosById.put(card.getStudentId(), subjectInfo))
            );

            List<Map.Entry<Long, SubjectInfo>> subjectEntries = new ArrayList<>();

            // sort the hashmap by average score
            subjectInfosById.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.comparingDouble(SubjectInfo::getAverage)))
                    .forEachOrdered(subjectEntries::add);

            for (int i = 0; i < subjectEntries.size(); i++) {
                subjectEntries.get(i).getValue().setSRank(subjectEntries.size() - i);
            }

            classReportCards.forEach(card -> {
                SubjectInfo subjectInfo = subjectInfosById.get(card.getStudentId());
                card.getSubjectInfos().stream().filter(s -> s.getId() == subjectInfo.getId())
                        .findFirst().ifPresent(s -> s.setSRank(subjectInfo.getSRank()));
            });
        });
    }
}
