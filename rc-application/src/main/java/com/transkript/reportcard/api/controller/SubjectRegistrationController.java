package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.service.SubjectRegistrationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/subject_registration")
public class SubjectRegistrationController {
    private final SubjectRegistrationService subjectRegistrationService;

    @PostMapping(value = "")
    public ResponseEntity<EntityResponse> addSubjectRegistration(@Valid @RequestBody SubjectRegistrationDto subjectRegistrationDto) {
        log.info("Add subject registration {}", subjectRegistrationDto);
        return new ResponseEntity<>(subjectRegistrationService.addSubjectRegistration(subjectRegistrationDto), HttpStatus.CREATED);
    }

    @PostMapping("/{applicationId}")
    public ResponseEntity<List<EntityResponse>> addSubjectRegistrations(@NotNull @PathVariable Long applicationId, @RequestBody List<SubjectRegistrationDto> subjectRegistrationDtoList) {
        log.info("Add subject registrations {}", subjectRegistrationDtoList.stream().map(SubjectRegistrationDto::getSubjectId).collect(Collectors.toList()));
        return new ResponseEntity<>(subjectRegistrationService.addSubjectRegistrations(applicationId, subjectRegistrationDtoList), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{applicationId}")
    public ResponseEntity<List<SubjectRegistrationDto>> getSubjectionRegistrations(@NotNull @PathVariable Long applicationId) {
        log.info("Get subject registrations for application {}", applicationId);
        return new ResponseEntity<>(subjectRegistrationService.getSubjectionRegistrations(applicationId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{registrationId}")
    public ResponseEntity<Void> deleteSubjectRegistration(@NotNull @PathVariable Long registrationId) {
        log.info("Delete subject registration {} ", registrationId);
        subjectRegistrationService.deleteSubjectRegistration(registrationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
