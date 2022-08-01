package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.ClassLevelDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.service.i.ClassLevelService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/class_level")
public class ClassLevelController {
    private final ClassLevelService classLevelService;
    @PostMapping
    public ResponseEntity<EntityResponse> create(@RequestBody ClassLevelDto classLevelDto) {
        log.info("Adding class level with name " + classLevelDto.getName());
        return new ResponseEntity<>(classLevelService.create(classLevelDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClassLevelDto>> getAll() {
        log.info("Getting class levels");
        return ResponseEntity.ok(classLevelService.getAllDtos());
    }

    @GetMapping(value = "/section", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClassLevelDto>> getAllBySection(@NotNull @RequestParam Long sectionId) {
        log.info("Getting class levels by section id: {}", sectionId);
        return ResponseEntity.ok(classLevelService.getAllDtosBySection(sectionId));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClassLevelDto> get(@PathVariable Long id) {
        log.info("Getting class level");
        return ResponseEntity.ok(classLevelService.getDto(id));
    }

    @PutMapping
    public ResponseEntity<EntityResponse> update(@RequestBody ClassLevelDto classLevelDto) {
        log.info("updating class level");
        return ResponseEntity.ok(classLevelService.update(classLevelDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        log.info("Deleting class level with id: " + id);
        classLevelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
