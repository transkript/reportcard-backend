package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.request.ClassListRequest;
import com.transkript.reportcard.api.dto.response.ClassListResponse;
import com.transkript.reportcard.business.service.interf.ClassListService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/class_list")
public class ClassListController {
    private final ClassListService classListService;

    @GetMapping
    public ResponseEntity<ClassListResponse> getClassList(
            @NotNull @RequestParam Long yearId,
            @NotNull @RequestParam Long classId,
            @NotNull @RequestParam Long subjectId,
            @NotNull @RequestParam Long sequenceId) {
        ClassListRequest request = new ClassListRequest(yearId, classId, subjectId, sequenceId);
        return ResponseEntity.ok(classListService.getClassList(request));
    }
}
