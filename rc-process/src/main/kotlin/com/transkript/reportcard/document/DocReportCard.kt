package com.transkript.reportcard.document

import com.transkript.reportcard.model.RcStudent
import java.time.format.DateTimeFormatter

class DocReportCard (private val rcStudent: RcStudent, private val termName: String, private val sequenceNames: List<String>) {
    val studentName: String = rcStudent.name
    val studentRegNum: String = rcStudent.regNum
    val studentGender: String = rcStudent.gender
    val studentDob: String = rcStudent.dob.format(DateTimeFormatter.ISO_DATE)
    val studentPob: String = rcStudent.pob
    val classLeve: String = rcStudent.rcClassLevel.name
    val classLevelSub: String = rcStudent.rcClassLevel.subName
    val year: String = rcStudent.rcClassLevel.academicYear
    val numOfSubjects: Int = rcStudent.rcSubjects.size

    val docSubjects: List<DocSubject> = listOf()

    init {

    }

    fun initialiseDocSubjects() {
        val rcSubjects = rcStudent.rcSubjects
        
        rcSubjects.forEach {  }
    }
}
