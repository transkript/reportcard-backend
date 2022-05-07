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
    @JsonProperty(namespace = "id")
    private Long id;

    @JsonProperty(namespace = "created_at")
    private LocalDateTime createdAt;

    // From relation
    @JsonProperty(namespace = "student_id")
    private Long studentId;

    @JsonProperty(namespace = "academic_year_id")
    private Long academicYearId;

    @JsonProperty(namespace = "number_of_subjects")
    private Integer numberOfSubjects;
}
