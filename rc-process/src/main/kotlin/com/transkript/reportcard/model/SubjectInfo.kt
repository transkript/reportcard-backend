package com.transkript.reportcard.model

import com.transkript.reportcard.enums.Remark
import kotlin.math.roundToInt

class SubjectInfo(val name: String, val coeff: Int, private val code: String, private val seqAGrade: GradeInfo, private val seqBGrade: GradeInfo) {
    private var avg = 0.0F

    var average: String = ""
    var total: String = ""
    var remark: String = ""
    val seqA: String = getSeqGrade(seqAGrade)
    val seqB: String = getSeqGrade(seqBGrade)

    init {
        this.avg = (seqAGrade.score + seqBGrade.score) / 2
        average = calcAverage()
        total = calcTotal()
        setRemarks()
    }

    override fun toString(): String {
        return "RcSubject(name='$name', coeff=$coeff, code='$code', seqAGradeInfo=$seqAGrade, seqBGradeInfo=$seqBGrade)"
    }

    private fun getSeqGrade(gradeInfo: GradeInfo): String {
        return if ((gradeInfo.score % 1) == 0.0F) {
            if (gradeInfo.graded) gradeInfo.score.roundToInt().toString() else "-"
        } else {
            if (gradeInfo.graded) gradeInfo.score.toString() else "-"
        }
    }

    private fun calcAverage(): String {
        return if ((avg % 1) == 0.0F) {
            avg.roundToInt().toString()
        } else {
            avg.toString()
        }
    }

    private fun calcTotal(): String {
        return String.format("%.2f", coeff * avg)
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


