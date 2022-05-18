package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.SequenceDto;
import com.transkript.reportcard.business.service.SequenceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/sequence")
public class SequenceController {

    private final SequenceService sequenceService;

    @PostMapping
    public ResponseEntity<String> addSequence(@RequestBody SequenceDto sequenceDto) {
        log.info("Adding section with name " + sequenceDto.getName());
        return new ResponseEntity<String>(sequenceService.addSequence(sequenceDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SequenceDto>> getSequences(){
        log.info("Getting all sections");
        return ResponseEntity.ok(sequenceService.getSequences());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SequenceDto> getSequence(
            @PathVariable Long id
    ){
        log.info("Getting the Sequence with id " + id);
        return ResponseEntity.ok(sequenceService.getSequence(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSequence(
            @PathVariable Long id,
            @RequestBody SequenceDto sequenceDto
    ) {
        log.info("Updating Sequence with id " + id);
        return ResponseEntity.ok(sequenceService.updateSequence(id, sequenceDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSequence(
            @PathVariable Long id
    ) {
        log.info("Deleting Sequence with id " + id);
        return ResponseEntity.ok(sequenceService.deleteSequence(id));
    }
}
