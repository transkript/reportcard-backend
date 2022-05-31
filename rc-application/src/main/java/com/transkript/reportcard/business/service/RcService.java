package com.transkript.reportcard.business.service;

import com.transkript.reportcard.api.dto.request.ReportCardRequest;

public interface RcService {
    void getReportCard(Long termId, ReportCardRequest reportCardRequest);
}
