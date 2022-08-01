package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.SchoolDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.service.i.SchoolService;
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
@RequestMapping(value = "/api/school")
public class SchoolController {
    private final SchoolService schoolService;

    @PostMapping
    public ResponseEntity<EntityResponse> create(@RequestBody SchoolDto schoolDto) {
        log.info("Adding school with name " + schoolDto.name());
        return new ResponseEntity<>(schoolService.create(schoolDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SchoolDto>> getAll() {
        log.info("Getting all schools");
        return ResponseEntity.ok(schoolService.getAllDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolDto> get(@PathVariable("id") Long id) {
        log.info("Getting school with Id: " + id);
        return ResponseEntity.ok(schoolService.getDto(id));
    }

    @PutMapping
    public ResponseEntity<EntityResponse> update(@RequestBody SchoolDto schoolDto) {
        log.info("Updating school with ID: " + schoolDto.id());
        return ResponseEntity.ok(schoolService.update(schoolDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        log.info("Deleting School with ID: " + id);
        schoolService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
