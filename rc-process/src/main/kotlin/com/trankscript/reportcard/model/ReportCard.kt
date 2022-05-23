package com.trankscript.reportcard.model

class ReportCard(val student: Student) {
    var average: Double = 0.0

    init {
        calculateAverage()
    }

    private fun calculateAverage() {
        var sumOfCoeff = 0
        var coeffByGradeValue = 0.0
        student.subjects.stream().peek{ subject: Subject ->
            val subjectValue = (subject.grade.score * subject.coeff).toDouble()
            coeffByGradeValue += subjectValue
        }.map(Subject::coeff).forEach { coeff: Int -> sumOfCoeff += coeff }
        average = coeffByGradeValue / sumOfCoeff
    }
}
