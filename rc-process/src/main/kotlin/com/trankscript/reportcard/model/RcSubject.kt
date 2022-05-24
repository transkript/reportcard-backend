package com.trankscript.reportcard.model

class RcSubject(val name: String, val coeff: Int, private val code: String, val rcGrades: List<RcGrade>) {
    var score = 0.0;

    init {
        calcScore()
    }

    override fun toString(): String {
        return "RcSubject(name='$name', coeff=$coeff, code='$code', rcGrades=list)"
    }

    public fun calcScore() {
        var gradeSum = 0.0
        val gradeValues = rcGrades.map { rcGrade -> rcGrade.score * coeff }.toList()
        for (value: Float in gradeValues) {
            gradeSum += value
        }
        score = gradeSum / gradeValues.size
    }
}
