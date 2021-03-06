package com.transkript.reportcard.model

import java.time.LocalDateTime

class StudentInfo(
    val name: String,
    val regNum: String,
    val gender: String,
    val dob: LocalDateTime,
    val pob: String,
    val repeating: Boolean
) {
    override fun toString(): String {
        return "RcStudent(name='$name', regNum='$regNum', dob=$dob, pob='$pob'"
    }
}
