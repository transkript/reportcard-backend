package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
public record SubjectRegistrationDto(
        @JsonProperty(value = "id") Long id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonProperty(value = "created_at") LocalDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonProperty(value = "updated_at") LocalDateTime updatedAt,

        // from relations
        @JsonProperty(value = "sat_id") @NotNull Long satId,
        @JsonProperty(value = "subject_id") @NotNull Long subjectId
) implements Serializable {
}
