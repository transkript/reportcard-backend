package com.trankscript.reportcard.process;

import com.trankscript.reportcard.model.ClassLevel;
import com.trankscript.reportcard.model.ReportCard;
import com.trankscript.reportcard.model.School;
import com.trankscript.reportcard.model.Student;
import com.trankscript.reportcard.model.Subject;

public class ReportCardProcess {
    private School school;
    private ClassLevel classLevel;
    private final ReportCard reportCard;

    public ReportCardProcess(Student student, School school, ClassLevel classLevel) {
        this.school = school;
        this.classLevel = classLevel;
        this.reportCard = new ReportCard(student);
    }

    private void calculateAverage() {
        Student student = this.reportCard.getStudent();
        final Integer[] sumOfCoeff = {0};
        final Double[] coeffByGradeValue = {0D};
        student.getSubjects().stream().peek(subject -> {
            double subjectValue = subject.getGrade().getScore() * subject.getCoeff();
            coeffByGradeValue[0] = coeffByGradeValue[0] + subjectValue;
        }).map(Subject::getCoeff).forEach(coeff -> sumOfCoeff[0] = sumOfCoeff[0] + coeff);

        this.reportCard.setAverage(coeffByGradeValue[0] / sumOfCoeff[0]);
    }

    private void prepareDocument() {

    }
}
