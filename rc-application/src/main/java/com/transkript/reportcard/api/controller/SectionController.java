package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.business.service.SectionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/section")
public class SectionController {
    SectionService sectionService;

    @PostMapping
    public ResponseEntity<String> addSection(@RequestBody SectionDto sectionDto){
        log.info("Saving Section with name" + sectionDto.getName());
        return new ResponseEntity<String>(sectionService.addSection(sectionDto), HttpStatus.CREATED) ;

    }
}
