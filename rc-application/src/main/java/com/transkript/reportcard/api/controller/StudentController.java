package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.StudentDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.service.i.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> create(@Valid @RequestBody StudentDto studentDto) {
        log.info("Adding student: {}", studentDto);
        return new ResponseEntity<>(studentService.addStudent(studentDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDto>> getStudents() {
        log.info("Getting students");
        return ResponseEntity.ok(studentService.getAllDto());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDto> get(@PathVariable Long id) {
        log.info("Getting student with id: {}", id);
        return ResponseEntity.ok(studentService.getDto(id));
    }

    @PutMapping
    public ResponseEntity<EntityResponse> update(@Valid @RequestBody StudentDto studentDto) {
        log.info("Updating student with id: {}", studentDto.id());
        return ResponseEntity.ok(studentService.update(studentDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        log.info("Deleting student with id: {}", id);
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
