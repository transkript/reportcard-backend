package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.ClassLevelDto;
import com.transkript.reportcard.business.service.ClassLevelService;
import com.transkript.reportcard.data.entity.ClassLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/classlevel")
public class ClassLevelController {

    private final ClassLevelService classLevelService;

    @PostMapping
    public ResponseEntity<String> addClassLevel(
            @RequestBody ClassLevelDto classLevelDto){
        log.info("Adding class level with name " + classLevelDto.getName());
        return new ResponseEntity<String>(classLevelService.addClassLevel(classLevelDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClassLevelDto>> getClassLevels() {
        log.info("Getting class levels");
        return ResponseEntity.ok(classLevelService.getClassLevels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassLevelDto> getClassLevel(@PathVariable("id") Long id) {
        log.info("Getting class level");
        return ResponseEntity.ok(classLevelService.getClassLevel(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClassLevel(
            @PathVariable("id") Long id,
            @RequestBody ClassLevelDto classLevelDto
    ) {
        log.info("updating class level");
        return ResponseEntity.ok(classLevelService.updateClassLevel(id, classLevelDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClassLevel(
            @PathVariable("id") Long id
    ) {
        log.info("Deleting class level with id: " + id);
        return ResponseEntity.ok(classLevelService.deleteClassLevel(id));
    }
}
