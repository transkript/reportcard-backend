package com.trankscript.reportcard.model

class RcGrade(
    val score: Float,
    private val desc: String,
    private val year: String,
    val term: String,
    val sequence: String
) {
    override fun toString(): String {
        return "RcGrade(score=$score, desc='$desc', year='$year', term='$term', sequence='$sequence')"
    }
}

