package com.trankscript.reportcard.model

class ReportCard(private val rcStudent: RcStudent) {
    var average: Double = 0.0

    init {
        calculateAverage()
    }

    private fun calculateAverage() {
        var sumOfCoeff = 0
        var coeffByGradeValue = 0.0
        rcStudent.rcSubjects.stream().peek { rcSubject: RcSubject ->
            val subjectValue = (rcSubject.rcGrade.score * rcSubject.coeff).toDouble()
            coeffByGradeValue += subjectValue
        }.map(RcSubject::coeff).forEach { coeff: Int -> sumOfCoeff += coeff }
        average = coeffByGradeValue / sumOfCoeff
    }

    override fun toString(): String {
        return "ReportCard(average=$average, rcStudent=$rcStudent)"
    }
}
