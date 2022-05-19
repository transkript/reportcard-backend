package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.service.StudentApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/student_application")
public class StudentApplicationController {
    private final StudentApplicationService studentApplicationService;

    @PostMapping(value = "")
    public ResponseEntity<EntityResponse> addStudentApplication(@RequestBody StudentApplicationDto studentApplicationDto) {
        log.info("Adding student application: {}", studentApplicationDto);
        return ResponseEntity.ok(studentApplicationService.addStudentApplication(studentApplicationDto));
    }

    @GetMapping(value = "")
    public ResponseEntity<List<StudentApplicationDto>> getStudentApplications() {
        log.info("Getting student applications");
        return ResponseEntity.ok(studentApplicationService.getStudentApplications());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentApplicationDto> getStudentApplicationById(@PathVariable Long id) {
        log.info("Getting student application by id: {}", id);
        return ResponseEntity.ok(studentApplicationService.getStudentApplicationById(id));
    }
}
