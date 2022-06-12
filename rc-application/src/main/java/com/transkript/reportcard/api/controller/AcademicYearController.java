package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.AcademicYearDto;
import com.transkript.reportcard.business.service.AcademicYearService;
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
@RequestMapping(value = "/api/academic_year")
public class AcademicYearController {
    private final AcademicYearService academicYearService;

    @PostMapping
    public ResponseEntity<String> addAcademicYear(
            @RequestBody AcademicYearDto academicYearDto) {
        log.info("Adding Academic Year with name " + academicYearDto.getName());
        return new ResponseEntity<>(academicYearService.addAcademicYear(academicYearDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AcademicYearDto>> getAcademicYears() {
        log.info("Getting the academic years");
        return ResponseEntity.ok(academicYearService.getAcademicYears());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicYearDto> getAcademicYear(@PathVariable("id") Long id) {
        log.info("Getting academic year with ID: " + id);
        return ResponseEntity.ok(academicYearService.getAcademicYear(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAcademicYear(
            @RequestBody AcademicYearDto academicYearDto,
            @PathVariable("id") Long id
    ) {
        log.info("Updating academic year with ID: " + id);
        return ResponseEntity.ok(academicYearService.updateAcademicYear(id, academicYearDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAcademicYear(
            @PathVariable("id") Long id) {
        log.info("Deleting academic year with ID: " + id);
        return ResponseEntity.ok(academicYearService.deleteAcademicYear(id));
    }
}
