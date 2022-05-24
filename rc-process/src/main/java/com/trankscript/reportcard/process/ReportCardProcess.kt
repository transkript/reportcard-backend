package com.trankscript.reportcard.process

import com.trankscript.reportcard.model.RcClassLevel
import com.trankscript.reportcard.model.RcSchool
import com.trankscript.reportcard.model.RcStudent
import com.trankscript.reportcard.model.ReportCard

class ReportCardProcess(rcStudent: RcStudent?, private val rcSchool: RcSchool, private val rcClassLevel: RcClassLevel) {
    private val reportCard: ReportCard

    init {
        reportCard = ReportCard(rcStudent!!)
    }


    private fun prepareDocument() {}
}
