package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.business.service.SectionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/section")
public class SectionController {

    private final SectionService sectionService;

    @PostMapping
    public ResponseEntity<String> addSection(@RequestBody SectionDto sectionDto){
        log.info("Adding section with name "+ sectionDto.getName());
        return new ResponseEntity<String>(sectionService.addSection(sectionDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SectionDto>> getSections() {
        log.info("Getting the sections");
        return ResponseEntity.ok(sectionService.getSections());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionDto> getSection(@PathVariable("id") Long id) {
        log.info("Getting the section with ID: " + id);
        return ResponseEntity.ok(sectionService.getSection(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSection(@PathVariable("id") Long id,
                                                @RequestBody SectionDto sectionDto) {
        log.info("Updating section with name ID: " + id);
        return ResponseEntity.ok(sectionService.updateSection(id, sectionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSection(@PathVariable("id") Long id) {
        log.info("Deleting section with id: " + id);
        return ResponseEntity.ok(sectionService.deleteSection(id));
    }
}
