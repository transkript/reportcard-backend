package com.transkript.reportcard.model

class RcSubject(val name: String, val coeff: Int, private val code: String, val grades: Map<String, RcGrade>) {
    var score = 0.0;

    init {
        calcScore()
    }

    override fun toString(): String {
        return "RcSubject(name='$name', coeff=$coeff, code='$code', grades=$grades)"
    }

    public fun calcScore() {

    }
}
