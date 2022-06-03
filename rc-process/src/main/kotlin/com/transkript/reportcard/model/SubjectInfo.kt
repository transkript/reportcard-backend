package com.transkript.reportcard.model

import com.transkript.reportcard.enums.Remark
import kotlin.math.roundToInt

class SubjectInfo(val id: Long, val name: String, val coeff: Int, private val code: String, private val seqAGrade: GradeInfo, private val seqBGrade: GradeInfo) {
    var average = 0.0F
    var total = 0.0F
    var sRank: Int = 0

    var averageLit: String = ""
    var totalLit: String = ""
    var remark: String = ""
    val seqA: String = getSeqGrade(seqAGrade)
    val seqB: String = getSeqGrade(seqBGrade)

    init {
        this.average = (seqAGrade.score + seqBGrade.score) / 2
        this.total =  coeff * average
        averageLit = calcAverage()
        totalLit = calcTotal()
        setRemarks()
    }

    override fun toString(): String {
        return "RcSubject(name='$name', coeff=$coeff, code='$code', average='$average', total='$total', rank='$sRank' seqAGradeInfo=$seqAGrade, seqBGradeInfo=$seqBGrade)"
    }

    private fun getSeqGrade(gradeInfo: GradeInfo): String {
        return if ((gradeInfo.score % 1) == 0.0F) {
            if (gradeInfo.graded) gradeInfo.score.roundToInt().toString() else "-"
        } else {
            if (gradeInfo.graded) gradeInfo.score.toString() else "-"
        }
    }

    private fun calcAverage(): String {
        return if ((average % 1) == 0.0F) {
            average.roundToInt().toString()
        } else {
            average.toString()
        }
    }

    private fun calcTotal(): String {
        return if ((total % 1) == 0.0F) {
            total.roundToInt().toString()
        } else {
            String.format("%.2f", total)
        }
    }

    private fun setRemarks() {
        remark = when (this.average.roundToInt()) {
            in 17..20 -> Remark.EXCELLENT.value
            in 14..15 -> Remark.VERY_GOOD.value
            in 11..13 -> Remark.GOOD.value
            10 -> Remark.AVERAGE.value
            in 7..9 -> Remark.POOR.value
            in 4..6 -> Remark.VERY_POOR.value
            in 0..3 -> Remark.FAIL.value
            else -> Remark.UNKNOWN.value
        }
    }
}


