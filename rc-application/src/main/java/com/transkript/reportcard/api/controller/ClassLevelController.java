package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.ClassLevelDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.service.interf.ClassLevelService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequestMapping(value = "/api/class_level")
public class ClassLevelController {

    private final ClassLevelService classLevelService;

    @PostMapping
    public ResponseEntity<EntityResponse> addClassLevel(@RequestBody ClassLevelDto classLevelDto) {
        log.info("Adding class level with name " + classLevelDto.getName());
        return new ResponseEntity<>(classLevelService.addClassLevel(classLevelDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClassLevelDto>> getClassLevels() {
        log.info("Getting class levels");
        return ResponseEntity.ok(classLevelService.getClassLevels());
    }

    @GetMapping(value = "/section", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClassLevelDto>> getClassLevels(@NotNull @RequestParam Long sectionId) {
        log.info("Getting class levels by section id: {}", sectionId);
        return ResponseEntity.ok(classLevelService.getClassLevelsBySection(sectionId));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClassLevelDto> getClassLevel(@PathVariable("id") Long id) {
        log.info("Getting class level");
        return ResponseEntity.ok(classLevelService.getClassLevel(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityResponse> updateClassLevel(
            @PathVariable("id") Long id,
            @RequestBody ClassLevelDto classLevelDto
    ) {
        log.info("updating class level");
        return ResponseEntity.ok(classLevelService.updateClassLevel(id, classLevelDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassLevel(@PathVariable("id") Long id) {
        log.info("Deleting class level with id: " + id);
        classLevelService.deleteClassLevel(id);
        return ResponseEntity.noContent().build();
    }
}
