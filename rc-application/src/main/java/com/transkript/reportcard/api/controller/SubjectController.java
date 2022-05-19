package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.SubjectDto;
import com.transkript.reportcard.business.service.SubjectService;
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
@RequestMapping(value = "/api/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getSubjects() {
        log.info("Getting All Subjects...");
        return ResponseEntity.ok(subjectService.getSubjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getSubject(@PathVariable("id") Long id) {
        log.info("Getting Subject with ID: " + id);
        return ResponseEntity.ok(subjectService.getSubject(id));
    }

    @PostMapping
    public ResponseEntity<String> addSubject(@RequestBody SubjectDto subjectDto) {
        log.info("Adding New Subject with ID: " + subjectDto.getId());
        return new ResponseEntity<>(subjectService.addSubject(subjectDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSubject(@PathVariable("id") Long id, @RequestBody SubjectDto subjectDto) {
        log.info("Updating Subject with ID: " + id);
        return ResponseEntity.ok(subjectService.updateSubject(id, subjectDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable("id") Long id) {
        log.info("Deleting Subject with ID: " + id);
        return ResponseEntity.ok(subjectService.deleteSubject(id));
    }
}
