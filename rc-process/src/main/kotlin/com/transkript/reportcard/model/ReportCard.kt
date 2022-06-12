package com.transkript.reportcard.model

class ReportCard(
    val studentId: Long,
    val schoolInfo: SchoolInfo,
    val studentInfo: StudentInfo,
    val classLevelInfo: ClassLevelInfo,
    val subjectInfos: MutableList<SubjectInfo>
) {
    var subjectsPassed: Int = 0
    var average: Double = 0.0
    var rank = -1
    var classAverage: Double = 0.0

    init {
        calculateAverage()
        calculateNumberOfSubjectsPassed()
    }

    private fun calculateAverage() {
        var totalScore = 0.0
        var totalCoeff = 0
        for (subjectInfo in subjectInfos) {
            totalScore += subjectInfo.total
            totalCoeff += subjectInfo.coeff
        }
        average = totalScore / totalCoeff
        println(average.toString() + " " + studentInfo.regNum)
    }

    private fun calculateNumberOfSubjectsPassed() {
        subjectsPassed = 0
        for (subjectInfo in subjectInfos) {
            if (subjectInfo.average >= 10) {
                subjectsPassed++
            }
        }
    }

    override fun toString(): String {
        return "ReportCard(average=$average, rcStudent=$studentInfo)"
    }
}
