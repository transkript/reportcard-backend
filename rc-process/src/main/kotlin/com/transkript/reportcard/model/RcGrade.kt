package com.transkript.reportcard.model

class RcGrade(val sequence: String, val score: Float) {
    override fun toString(): String {
        return "RcGrade(sequence='$sequence', score=$score)"
    }
}

