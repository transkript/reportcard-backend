package com.transkript.reportcard.model

class ReportCard(
    val rcSchool: RcSchool,
    val rcStudent: RcStudent,
    val termName: String,
    val sequenceNames: MutableList<String>
) {
    var average: Double = 0.0


    init {
        calculateAverage()
    }

    private fun calculateAverage() {
        var sumOfCoeff = 0
        var coeffByGradeValue = 0.0
        rcStudent.rcSubjects.stream().peek { rcSubject: RcSubject ->
            rcSubject.calcScore()
            val subjectValue = rcSubject.score
            coeffByGradeValue += subjectValue
        }.map(RcSubject::coeff).forEach { coeff: Int -> sumOfCoeff += coeff }
        average = coeffByGradeValue / sumOfCoeff
    }

    override fun toString(): String {
        return "ReportCard(average=$average, rcStudent=$rcStudent)"
    }
}
