package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public record StudentApplicationTrialDto(
        Long id,
        Integer order,
        Boolean repeating,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime updatedAt,
        Long academicYearId,
        Integer numberOfSubjects,
        StudentApplicationDto.ApplicationKeyDto applicationKey
) implements Serializable {

}
