package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.AcademicYearDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.service.i.AcademicYearService;
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
@RequestMapping(value = "/api/academic_year")
public class AcademicYearController {
    private final AcademicYearService academicYearService;

    @PostMapping
    public ResponseEntity<EntityResponse> create(@RequestBody AcademicYearDto academicYearDto) {
        log.info("Adding Academic Year with name " + academicYearDto.getName());
        return new ResponseEntity<>(academicYearService.create(academicYearDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AcademicYearDto>> getAll() {
        log.info("Getting the academic years");
        return ResponseEntity.ok(academicYearService.getAllDtos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicYearDto> get(@PathVariable("id") Long id) {
        log.info("Getting academic year with ID: " + id);
        return ResponseEntity.ok(academicYearService.getDto(id));
    }

    @PutMapping()
    public ResponseEntity<EntityResponse> update(@RequestBody AcademicYearDto academicYearDto) {
        log.info("Updating academic year with ID: " + academicYearDto.getId());
        return ResponseEntity.ok(academicYearService.update(academicYearDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAcademicYear(@PathVariable Long id) {
        log.info("Deleting academic year with ID: " + id);
        academicYearService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
