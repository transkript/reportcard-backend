package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.request.ReportCardRequest;
import com.transkript.reportcard.business.service.RcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/rc")
public class RcController {
    private final RcService rcService;

    @GetMapping(value = "/reportcard/{termId}")
    public void getReportCard(@NotNull @PathVariable Long termId, @Valid @RequestBody ReportCardRequest reportCardRequest) {
        rcService.getReportCard(termId, reportCardRequest);
    }
}
