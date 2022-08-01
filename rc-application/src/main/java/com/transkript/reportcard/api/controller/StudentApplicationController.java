package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.api.dto.StudentApplicationTrialDto;
import com.transkript.reportcard.api.dto.request.StudentApplicationRequest;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.api.dto.response.StudentApplicationResponse;
import com.transkript.reportcard.business.service.i.StudentApplicationService;
import com.transkript.reportcard.business.service.i.StudentApplicationTrialService;
import com.transkript.reportcard.exception.ReportCardException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private final StudentApplicationTrialService studentApplicationTrialService;

    @PostMapping(value = "")
    public ResponseEntity<EntityResponse> create(@Valid @RequestBody StudentApplicationRequest applicationRequest) {
        log.info("Adding student application: {}", applicationRequest);
        return new ResponseEntity<>(studentApplicationService.create(applicationRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<StudentApplicationDto>> getAll() {
        log.info("Getting student applications");
        return ResponseEntity.ok(studentApplicationService.getAllAsDto());
    }

    @GetMapping(value = "/sat")
    public ResponseEntity<List<StudentApplicationTrialDto>> getTrialsByYearAndClass(@RequestParam @NotNull Long classSubId, @RequestParam @NotNull Long yearId) {
        return ResponseEntity.ok(studentApplicationTrialService.getAllTrialsByYearAndClass(yearId, classSubId));
    }

    @GetMapping(value = "/key")
    public ResponseEntity<StudentApplicationDto> get(
            @RequestBody StudentApplicationDto.ApplicationKeyDto keyDto
    ) {
        log.info("Getting student application by request {}", keyDto);
        return ResponseEntity.ok(studentApplicationService.getDto(keyDto));
    }

    @GetMapping(value = "one_full")
    public ResponseEntity<StudentApplicationResponse> readFull(
            @RequestParam @NotNull Long classId,
            @RequestParam @NotNull Long studentId,
            @RequestParam @NotNull Long yearId
    ) {
        var request = new StudentApplicationRequest(classId, yearId, studentId);
        log.info("Getting student application response by request {}", request);
        if (request.studentId() == null) {
            throw new ReportCardException.IllegalStateException("Student ID cannot be null");
        }
        return ResponseEntity.ok(studentApplicationService.getAsResponse(request));
    }

    @GetMapping(value = "/all_full")
    public ResponseEntity<List<StudentApplicationResponse>> getAllByRequest(@RequestParam @NotNull Long classId, @RequestParam @NotNull Long yearId) {
        StudentApplicationRequest request = new StudentApplicationRequest(classId, yearId, null);
        log.info("Getting student applications by request: {}", request);
        return ResponseEntity.ok(studentApplicationService.getAllAsResponses(request));
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Void> delete(@RequestBody StudentApplicationDto.ApplicationKeyDto keyDto) {
        log.info("Deleting student application by request {}", keyDto);
        studentApplicationService.delete(keyDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "")
    public ResponseEntity<EntityResponse> update() {
        return null;
    }
}
