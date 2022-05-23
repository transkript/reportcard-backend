package com.trankscript.reportcard.model

import java.time.LocalDateTime

class Student(val name: String, val regNum: String, val gender: String, val dob: LocalDateTime, val pob: String,
              val classLevel: ClassLevel, val subjects: List<Subject>) {

}
