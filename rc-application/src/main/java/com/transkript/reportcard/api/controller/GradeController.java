package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.GradeDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.service.i.GradeService;
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
    public ResponseEntity<EntityResponse> create(@Valid @RequestBody GradeDto gradeDto) {
        log.info("Adding grade for registration and sequence: {}", gradeDto.key());
        return new ResponseEntity<>(gradeService.create(gradeDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/sequence/{sequenceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GradeDto>> getAllBySequence(@NotNull @PathVariable Long sequenceId) {
        log.info("Getting all grades of sequenceId {}", sequenceId);
        return ResponseEntity.ok(gradeService.getAllDtoBySequence(sequenceId));
    }

    @GetMapping(value = "/registration/{registrationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GradeDto>> getAllByRegistration(@PathVariable Long registrationId) {
        return ResponseEntity.ok(gradeService.getAllDtoByRegistration(registrationId));
    }

    @GetMapping(value = "/key", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GradeDto> get(@RequestBody GradeDto.GradeKeyDto keyDto) {
        log.info("Getting all grades of registration and sequence: {}", keyDto);
        return ResponseEntity.ok(gradeService.getDto(keyDto));
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityResponse> update(@Valid @RequestBody GradeDto gradeDto) {
        log.info("Updating grade for registration and sequence: {}", gradeDto.key());
        return ResponseEntity.ok(gradeService.update(gradeDto));
    }


}
