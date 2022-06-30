package com.transkript.reportcard.business.service;

import com.transkript.reportcard.api.dto.request.ReportCardRequest;

import java.io.File;

public interface RcService {
    File getReportCard(Long termId, ReportCardRequest reportCardRequest);
}
