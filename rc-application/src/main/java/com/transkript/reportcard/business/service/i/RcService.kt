package com.transkript.reportcard.business.service.i

import com.transkript.reportcard.api.dto.request.ReportCardRequest
import java.io.File

interface RcService {
    fun getReportCard(termId: Long?, reportCardRequest: ReportCardRequest?): File?
}
