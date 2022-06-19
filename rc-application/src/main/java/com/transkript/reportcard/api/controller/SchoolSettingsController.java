package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.SchoolSettingsDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.service.SchoolSettingsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/settings")
public class SchoolSettingsController {
    private final SchoolSettingsService schoolSettingsService;

    @PostMapping
    public ResponseEntity<EntityResponse> addSettings(@Valid @RequestBody SchoolSettingsDto schoolSettingsDto) {
        log.info("Saving settings for school system");
        return new ResponseEntity<>(schoolSettingsService.saveSettings(schoolSettingsDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EntityResponse> updateSettings(@Valid @RequestBody SchoolSettingsDto schoolSettingsDto) {
        log.info("Updating settings for school system");
        return ResponseEntity.ok(schoolSettingsService.saveSettings(schoolSettingsDto));
    }

    @GetMapping
    public ResponseEntity<SchoolSettingsDto> getSettings() {
        log.info("Retrieving settings");
        return ResponseEntity.ok(schoolSettingsService.getSettings());
    }
}