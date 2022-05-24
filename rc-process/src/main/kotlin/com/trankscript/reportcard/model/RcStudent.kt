package com.trankscript.reportcard.model

import java.time.LocalDateTime

class RcStudent(val name: String, val regNum: String, val gender: String, val dob: LocalDateTime, val pob: String,
                val rcClassLevel: RcClassLevel, val rcSubjects: List<RcSubject>) {
    override fun toString(): String {
        return "RcStudent(name='$name', regNum='$regNum', dob=$dob, pob='$pob', rcClassLevel=$rcClassLevel, rcSubjects=list)"
    }
}
