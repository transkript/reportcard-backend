package com.transkript.reportcard.model

import com.transkript.reportcard.enums.Remark
import kotlin.math.roundToInt

class SubjectInfo(val name: String, val coeff: Int, private val code: String, private val seqAGrade: GradeInfo, private val seqBGrade: GradeInfo) {
    var average: Double = 0.0
    var total: Double = 0.0
    var remark: String = ""
    val seqA: String = if (seqAGrade.graded) seqAGrade.score.toString() else "-"
    val seqB: String = if (seqBGrade.graded) seqBGrade.score.toString() else "-"

    init {
        average = calcAverage()
        total = calcTotal()
        setRemarks()
    }

    override fun toString(): String {
        return "RcSubject(name='$name', coeff=$coeff, code='$code', seqAGradeInfo=$seqAGrade, seqBGradeInfo=$seqBGrade)"
    }

    private fun calcAverage(): Double {
        return ((seqAGrade.score + seqBGrade.score) / 2).toDouble()
    }

    private fun calcTotal(): Double {
        val t = String.format("%.2f", this.coeff * this.average)
        return t.toDouble()
    }

    private fun setRemarks() {
        when (this.average.roundToInt()) {
            in 17..20 -> {
                remark = Remark.EXCELLENT.value
            }
            in 14..15 -> {
                remark = Remark.VERY_GOOD.value
            }
            in 11..13 -> {
                remark = Remark.GOOD.value
            }
            10 -> {
                remark = Remark.AVERAGE.value
            }
            in 7..9 -> {
                remark = Remark.POOR.value
            }
            in 4..6 -> {
                remark = Remark.VERY_POOR.value
            }
            in 0..3 -> {
                remark = Remark.FAIL.value
            }
            else -> {
                remark = Remark.UNKNOWN.value
            }
        }
    }
}


