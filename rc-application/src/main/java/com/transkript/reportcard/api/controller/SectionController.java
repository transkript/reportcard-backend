package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.business.service.SectionService;
import com.transkript.reportcard.data.entity.Section;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/section")
public class SectionController {
    SectionService sectionService;

    @GetMapping("/{id}")
    public ResponseEntity<SectionDto> getSection(@PathVariable("id") Long id){
        log.info("Getting Section by ID: "+ id);
        return ResponseEntity.ok(sectionService.getSection(id));
    }
    @GetMapping
    public ResponseEntity<List<SectionDto>> getSections(){
        log.info("Getting All Sections");
        return ResponseEntity.ok(sectionService.getSections());
    }

    @PostMapping
    public ResponseEntity<String> addSection(@RequestBody SectionDto sectionDto){
        log.info("Saving Section with name" + sectionDto.getName());
        return new ResponseEntity<String>(sectionService.addSection(sectionDto), HttpStatus.CREATED) ;

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSection(@PathVariable("id") Long id, @RequestBody SectionDto sectionDto){
        log.info("Updating Section with ID: " + id);
        return ResponseEntity.ok(sectionService.updateSection(id, sectionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSection(@PathVariable("id") Long id){
        log.info("Deleting Section with Id: "+ id);
        return ResponseEntity.ok(sectionService.deleteSection(id));

    }

}
