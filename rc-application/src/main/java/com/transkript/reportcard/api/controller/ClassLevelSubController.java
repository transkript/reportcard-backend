package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.ClassLevelSubDto;
import com.transkript.reportcard.business.service.ClassLevelSubService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/classlevelsub")
public class ClassLevelSubController {

    private final ClassLevelSubService classLevelSubService;

    @PostMapping
    public ResponseEntity<String> addClassLevelSub(
            @RequestBody ClassLevelSubDto classLevelSubDto
            ) {
        log.info("Adding Class level sub with Name " + classLevelSubDto.getName());
        return new ResponseEntity<String>(classLevelSubService.addClassLevelSub(classLevelSubDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClassLevelSubDto>> getClassLevelSubs() {
        log.info("Getting all class level subs");
        return ResponseEntity.ok(classLevelSubService.getClassLevelSubs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassLevelSubDto> getClassLevelSub(
            @PathVariable("id") Long id
    ) {
        log.info("Getting class level sub with id: " + id);
        return ResponseEntity.ok(classLevelSubService.getClassLevelSub(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClassLevelSub(
            @PathVariable("id") Long id,
            @RequestBody ClassLevelSubDto classLevelSubDto
    ) {
        log.info("Updating class level sub with id: " + id);
        return ResponseEntity.ok(classLevelSubService.updateClassLevelSub(id, classLevelSubDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteClassLevelSub(
            @PathVariable("id") Long id
    ){
        log.info("Deleting class level sub with id: " + id);
        return ResponseEntity.ok(classLevelSubService.deleteClassLevelSub(id));
    }
}
