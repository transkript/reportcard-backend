package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.business.service.RcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/rc")
public class RcController {
    private final RcService rcService;

    @GetMapping(value = "/reportcard/{sequenceId}")
    public void getReportCard(@NotNull @RequestParam Long studentId, @NotNull @RequestParam Long yearId, @NotNull @PathVariable Long sequenceId) {
        rcService.getReportCard(studentId, yearId, sequenceId);
    }
}
