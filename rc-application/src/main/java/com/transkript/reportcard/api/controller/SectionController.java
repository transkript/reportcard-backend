package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.business.service.interf.SectionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/section")
public class SectionController {
    SectionService sectionService;

    @GetMapping("/{id}")
    public ResponseEntity<SectionDto> getSection(@PathVariable("id") Long id) {
        log.info("Getting Section by ID: " + id);
        return ResponseEntity.ok(sectionService.getSection(id));
    }

    @GetMapping
    public ResponseEntity<List<SectionDto>> getSections() {
        log.info("Getting All Sections");
        return ResponseEntity.ok(sectionService.getSections());
    }

    @PostMapping
    public ResponseEntity<String> addSection(@RequestBody SectionDto sectionDto) {
        log.info("Saving Section with name" + sectionDto.getName());
        return new ResponseEntity<>(sectionService.addSection(sectionDto), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSection(@PathVariable("id") Long id, @RequestBody SectionDto sectionDto) {
        log.info("Updating Section with ID: " + id);
        return ResponseEntity.ok(sectionService.updateSection(id, sectionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSection(@PathVariable("id") Long id) {
        log.info("Deleting Section with Id: " + id);
        return ResponseEntity.ok(sectionService.deleteSection(id));

    }

}
