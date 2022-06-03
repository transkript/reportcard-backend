package com.transkript.reportcard.model

class GradeInfo(val score: Float, val graded: Boolean) {
    override fun toString(): String {
        return "RcGrade(score=$score, graded=$graded)"
    }
}

