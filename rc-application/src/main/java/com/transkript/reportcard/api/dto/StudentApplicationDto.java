package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentApplicationDto {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;

    // From relation
    @JsonProperty(value = "student_id")
    private Long studentId;

    @JsonProperty(value = "academic_year_id")
    private Long academicYearId;

    @JsonProperty(value = "number_of_subjects")
    private Integer numberOfSubjects;
}
