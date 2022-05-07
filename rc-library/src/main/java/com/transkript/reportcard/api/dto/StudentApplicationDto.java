package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
    private Long studentName;

    @JsonProperty(namespace = "academic_year_name")
    private String academicYearName;

    @JsonProperty(namespace = "number_of_subjects")
    private Integer numberOfSubjects;
}
