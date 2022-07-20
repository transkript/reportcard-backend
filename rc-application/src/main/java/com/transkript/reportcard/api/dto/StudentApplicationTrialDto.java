package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public record StudentApplicationTrialDto(
        Long id,
        Integer order,
        @JsonProperty(value = "repeating") Boolean repeating,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonProperty(value = "created_at") LocalDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonProperty(value = "updated_at") LocalDateTime updatedAt,
        @JsonProperty(value = "academicYearId") Long academicYearId,
        @JsonProperty(value = "num_of_subjects") Integer numberOfSubjects,
        @JsonProperty(value = "application_key") StudentApplicationDto.ApplicationKeyDto applicationKeyDto
) implements Serializable {

}
