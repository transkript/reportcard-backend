package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.SubjectDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.service.i.SubjectService;
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
@RequestMapping(value = "/api/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<EntityResponse> create(@RequestBody SubjectDto subjectDto) {
        log.info("Adding New Subject with ID: " + subjectDto.id());
        return new ResponseEntity<>(subjectService.create(subjectDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getSubjects() {
        log.info("Getting All Subjects...");
        return ResponseEntity.ok(subjectService.getAllDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> get(@PathVariable Long id) {
        log.info("Getting Subject with ID: " + id);
        return ResponseEntity.ok(subjectService.getDto(id));
    }

    @PutMapping
    public ResponseEntity<EntityResponse> update(@RequestBody SubjectDto subjectDto) {
        log.info("Updating Subject with ID: " + subjectDto.id());
        return ResponseEntity.ok(subjectService.update(subjectDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deleting Subject with ID: " + id);
        subjectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
