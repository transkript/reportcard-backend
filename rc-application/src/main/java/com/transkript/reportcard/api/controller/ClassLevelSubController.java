package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.ClassLevelSubDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.service.i.ClassLevelSubService;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/class_level_sub")
public class ClassLevelSubController {

    private final ClassLevelSubService classLevelSubService;

    @PostMapping
    public ResponseEntity<EntityResponse> addClassLevelSub(@RequestBody ClassLevelSubDto classLevelSubDto) {
        log.info("Adding Class level sub with Name " + classLevelSubDto.getName());
        return new ResponseEntity<>(classLevelSubService.addClassLevelSub(classLevelSubDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClassLevelSubDto>> getClassLevelSubs() {
        log.info("Getting all class level subs");
        return ResponseEntity.ok(classLevelSubService.getClassLevelSubs());
    }

    @GetMapping(value = "/level/{levelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClassLevelSubDto>> getClassLevelSubsByClassLevel(@NotNull @PathVariable("levelId") Long levelId) {
        log.info("Getting class level subs with class level id: " + levelId);
        return ResponseEntity.ok(classLevelSubService.getClassLevelSubsByClassLevel(levelId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassLevelSubDto> getClassLevelSub(@PathVariable("id") Long id) {
        log.info("Getting class level sub with id: " + id);
        return ResponseEntity.ok(classLevelSubService.getClassLevelSub(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityResponse> updateClassLevelSub(@PathVariable("id") Long id, @RequestBody ClassLevelSubDto classLevelSubDto) {
        log.info("Updating class level sub with id: " + id);
        return ResponseEntity.ok(classLevelSubService.updateClassLevelSub(id, classLevelSubDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClassLevelSub(@PathVariable("id") Long id) {
        log.info("Deleting class level sub with id: " + id);
        classLevelSubService.deleteClassLevelSub(id);
        return ResponseEntity.noContent().build();
    }
}
