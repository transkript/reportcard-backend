package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.SchoolDto;
import com.transkript.reportcard.business.service.SchoolService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/school")
public class SchoolController {

    private final SchoolService schoolService;


    @PostMapping
    public ResponseEntity<String> addSchool(@RequestBody SchoolDto schoolDto){
        log.info("Adding school with name "+ schoolDto.getName());
        return new ResponseEntity<String>(schoolService.addSchool(schoolDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SchoolDto>> getSchools(){
        log.info("Getting all schools");
        return ResponseEntity.ok(schoolService.getSchools());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolDto> getSchool(@PathVariable("id") Long id){
        log.info("Getting school with Id: " + id);
        return ResponseEntity.ok(schoolService.getSchool(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSchool( @PathVariable("id") Long id, @RequestBody SchoolDto schoolDto){
        log.info("Updating school with ID: "+ id);
        return ResponseEntity.ok(schoolService.updateSchool(id, schoolDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchool(@PathVariable("id") Long id){
        log.info("Deleting School with ID: "+id);
        return ResponseEntity.ok(schoolService.deleteSchool(id));
    }
}
