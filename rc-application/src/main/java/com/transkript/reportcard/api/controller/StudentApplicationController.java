package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.service.StudentApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/student_application")
public class StudentApplicationController {
    private final StudentApplicationService studentApplicationService;

    @PostMapping(value = "")
    public ResponseEntity<EntityResponse> addStudentApplication(@Valid @RequestBody StudentApplicationDto studentApplicationDto) {
        log.info("Adding student application: {}", studentApplicationDto);
        return ResponseEntity.ok(studentApplicationService.addStudentApplication(studentApplicationDto));
    }

    @GetMapping(value = "")
    public ResponseEntity<List<StudentApplicationDto>> getStudentApplications() {
        log.info("Getting student applications");
        return ResponseEntity.ok(studentApplicationService.getStudentApplications());
    }

    @GetMapping(value = "/")
    public ResponseEntity<StudentApplicationDto> getStudentApplication(@RequestParam @NotNull Long studentId, @RequestParam @NotNull Long yearId) {
        log.info("Getting student application by student id: {}, year id: {}", studentId, yearId);
        return ResponseEntity.ok(studentApplicationService.getStudentApplication(studentId, yearId));
    }
}
