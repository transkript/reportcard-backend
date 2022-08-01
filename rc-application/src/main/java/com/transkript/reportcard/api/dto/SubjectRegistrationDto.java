package com.transkript.reportcard.api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record SubjectRegistrationDto(
        SubjectRegistrationKeyDto key,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<GradeDto> grades,
        Long subjectId,
        Long satId
) implements Serializable {
    public record SubjectRegistrationKeyDto(Long subjectId, Long satId) implements Serializable {
    }
}
