package com.transkript.reportcard.api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record SubjectRegistrationDto(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long subjectId,
                                     Long satId) implements Serializable {
}
