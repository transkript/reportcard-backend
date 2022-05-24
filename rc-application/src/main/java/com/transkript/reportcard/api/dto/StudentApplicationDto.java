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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentApplicationDto {
    @Builder.Default
    @JsonProperty(value = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt = LocalDateTime.now();

    // From relation
    @JsonProperty(value = "student_id")
    @NotNull
    private Long studentId;

    @JsonProperty(value = "year_id")
    @NotNull
    private Long academicYearId;

    @JsonProperty(value = "number_of_subjects")
    private Integer numberOfSubjects;

    @JsonProperty(value = "class_sub_id")
    @NotNull
    private Long classLevelSubId;
}
