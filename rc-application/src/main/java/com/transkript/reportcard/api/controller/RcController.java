package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.request.ReportCardRequest;
import com.transkript.reportcard.business.service.RcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/rc")
public class RcController {
    private final RcService rcService;

    @GetMapping(value = "/reportcard/{termId}")
    @ResponseBody
    public ResponseEntity<Resource> getReportCard(@NotNull @PathVariable Long termId, @Valid @RequestBody ReportCardRequest reportCardRequest) throws FileNotFoundException {
        File reportCardFile = rcService.getReportCard(termId, reportCardRequest);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(reportCardFile));
        return ResponseEntity.ok().contentLength(reportCardFile.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }
}
