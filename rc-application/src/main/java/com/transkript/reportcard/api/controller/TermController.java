package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.TermDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.service.i.TermService;
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
@RequestMapping(value = "/api/term")
public class TermController {

    private final TermService termService;

    @PostMapping
    public ResponseEntity<EntityResponse> create(@RequestBody TermDto termDto) {
        log.info("Adding a term with Name: " + termDto.name());
        return new ResponseEntity<>(termService.create(termDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TermDto>> getTerms() {
        log.info("Getting all terms");
        return ResponseEntity.ok(termService.getAllDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TermDto> getTerm(@PathVariable Long id) {
        log.info("Getting term with id: " + id);
        return ResponseEntity.ok(termService.getDto(id));
    }

    @PutMapping
    public ResponseEntity<EntityResponse> updateTerm(@RequestBody TermDto termDto) {
        log.info("Updating term with id: " + termDto.id());
        return ResponseEntity.ok(termService.update(termDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTerm(@PathVariable Long id) {
        log.info("Deleting term with id: " + id);
        termService.delete(id);
        return ResponseEntity.notFound().build();
    }
}
