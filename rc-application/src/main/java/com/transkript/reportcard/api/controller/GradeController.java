package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.GradeDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.service.GradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "/api/grade")
public class GradeController {
    private final GradeService gradeService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> addGrade(@Valid @RequestBody GradeDto gradeDto) {
        log.info("Adding grade for registration {} and sequence {}", gradeDto.getRegistrationId(), gradeDto.getSequenceId());
        return new ResponseEntity<>(gradeService.addGrade(gradeDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/sequence/{sequenceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GradeDto>> getGradesBySequence(@NotNull @PathVariable Long sequenceId) {
        log.info("Getting all grades of sequenceId {}", sequenceId);
        return ResponseEntity.ok(gradeService.getGradesBySequence(sequenceId));
    }

    @GetMapping(value = "/registration/{registrationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GradeDto>> getGradesByRegistration(@NotNull @PathVariable Long registrationId) {
        return ResponseEntity.ok(gradeService.getGradesByRegistration(registrationId));
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GradeDto> getGrade(
            @NotNull @RequestParam Long registrationId,
            @NotNull @RequestParam Long sequenceId) {
        log.info("Getting all grades of registrationId {}, and sequenceId {}", registrationId, sequenceId);
        return ResponseEntity.ok(gradeService.getGrade(registrationId, sequenceId));
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> updateGrade(@Valid @RequestBody GradeDto gradeDto) {
        log.info("Updating grade for registration {} and sequence {}", gradeDto.getRegistrationId(), gradeDto.getSequenceId());
        return ResponseEntity.ok(gradeService.updateGrade(gradeDto));
    }


}
