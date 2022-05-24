package com.trankscript.reportcard.process

import com.trankscript.reportcard.model.*

class ReportCardProcess(rcStudent: RcStudent?, private val rcSchool: RcSchool, private val rcClassLevel: RcClassLevel) {
    private val reportCard: ReportCard

    init {
        reportCard = ReportCard(rcStudent!!)
    }


    private fun prepareDocument() {}
}
