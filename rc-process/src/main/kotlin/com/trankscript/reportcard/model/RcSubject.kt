package com.trankscript.reportcard.model

class RcSubject(val name: String, val coeff: Int, private val code: String, val rcGrade: RcGrade) {
    override fun toString(): String {
        return "RcSubject(name='$name', coeff=$coeff, code='$code', rcGrade=$rcGrade)"
    }
}
