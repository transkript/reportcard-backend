package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.TermDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.service.TermService;
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
@RequestMapping(value = "/api/term")
public class TermController {

    private final TermService termService;

    @PostMapping
    public ResponseEntity<EntityResponse> addTerm(@RequestBody TermDto termDto) {
        log.info("Adding a term with Name: " + termDto.name());
        return new ResponseEntity<>(termService.addTerm(termDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TermDto>> getTerms() {
        log.info("Getting all terms");
        return ResponseEntity.ok(termService.getTerms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TermDto> getTerm(
            @PathVariable("id") Long id
    ) {
        log.info("Getting term with id: " + id);
        return ResponseEntity.ok(termService.getTerm(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityResponse> updateTerm(@PathVariable("id") Long id, @RequestBody TermDto termDto) {
        log.info("Updating term with id: " + id);
        return ResponseEntity.ok(termService.updateTerm(id, termDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTerm(
            @PathVariable("id") Long id
    ) {
        log.info("Deleting term with id: " + id);
        return ResponseEntity.ok(termService.deleteTerm(id));
    }
}
