package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.service.i.SectionService;
import lombok.RequiredArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping(value = "/api/section")
public class SectionController {
    private final SectionService sectionService;

    @GetMapping("/{id}")
    public ResponseEntity<SectionDto> get(@PathVariable Long id) {
        log.info("Getting Section by ID: " + id);
        return ResponseEntity.ok(sectionService.getDto(id));
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<SectionDto>> getBySchoolId(@PathVariable Long schoolId) {
        log.info("Getting Section by school id: {}", schoolId);
        return ResponseEntity.ok(sectionService.getDtoBySchoolId(schoolId));
    }


    @GetMapping
    public ResponseEntity<List<SectionDto>> getAll() {
        log.info("Getting All Sections");
        return ResponseEntity.ok(sectionService.getAllDto());
    }

    @PostMapping
    public ResponseEntity<EntityResponse> create(@RequestBody SectionDto sectionDto) {
        log.info("Saving Section with name" + sectionDto.getName());
        return new ResponseEntity<>(sectionService.create(sectionDto), HttpStatus.CREATED);

    }

    @PutMapping("")
    public ResponseEntity<EntityResponse> update(@RequestBody SectionDto sectionDto) {
        log.info("Updating section with ID: " + sectionDto.getId());
        return ResponseEntity.ok(sectionService.update(sectionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deleting Section with Id: " + id);
        sectionService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
