package com.transkript.reportcard.document

import com.transkript.reportcard.model.ReportCard
import com.transkript.reportcard.model.StudentInfo
import com.transkript.reportcard.model.SubjectInfo
import java.time.format.DateTimeFormatter

class DocReportCard(private val card: ReportCard) {
    private val studentInfo: StudentInfo = card.studentInfo

    // set average to 2 decimal places
    val average: String = String.format("%.2f", card.average)
    val classAverage: String = String.format("%.2f", card.classAverage)
    val classRank: Int = card.rank

    val studentClassId: Long = card.studentId
    val schoolName: String = card.schoolInfo.name
    val studentName: String = studentInfo.name
    val studentRegNum: String = studentInfo.regNum
    val studentGender: String = studentInfo.gender
    val studentDob: String = studentInfo.dob.format(DateTimeFormatter.ISO_DATE)
    val studentPob: String = studentInfo.pob
    val studentRepeating: String = if (studentInfo.repeating) "YES" else "NO"

    val classLevel: String = card.classLevelInfo.name
    val classLevelSub: String = card.classLevelInfo.subName

    val year: String = card.schoolInfo.yearName
    val term: String = card.schoolInfo.termName
    val seqAName: String = card.schoolInfo.seqAName
    val seqBName: String = card.schoolInfo.seqBName

    val numOfSubjects: Int = card.subjectInfos.size
    val subjectsPassed: String = card.subjectsPassed.toString() + "/" + card.subjectInfos.size

    val subjects: List<SubjectInfo> = card.subjectInfos

    fun getClassName(): String {
        return "$classLevel $classLevelSub"
    }
}
