package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRegistrationDto {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty(value = "updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    // from relations
    @JsonProperty(value = "student_id")
    @NotNull
    private Long studentId;

    @JsonProperty(value = "year_id")
    private Long yearId;

    @JsonProperty(value = "subject_id")
    @NotNull
    private Long subjectId;
}
