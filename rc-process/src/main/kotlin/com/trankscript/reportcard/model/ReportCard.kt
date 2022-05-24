package com.trankscript.reportcard.model

import java.time.format.DateTimeFormatter

class ReportCard(private val rcStudent: RcStudent) {
    var average: Double = 0.0
    val studentName: String = rcStudent.name
    val studentRegNum: String = rcStudent.regNum
    val studentGender: String = rcStudent.gender
    val studentDob: String = rcStudent.dob.format(DateTimeFormatter.ISO_DATE)
    val studentPob: String = rcStudent.pob
    val classLeve: String = rcStudent.rcClassLevel.name
    val classLevelSub: String = rcStudent.rcClassLevel.subName
    val academicYear: String = rcStudent.rcClassLevel.academicYear
    val numOfSubjects: Int = rcStudent.rcSubjects.size

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
