package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

public record StudentApplicationDto (
        @JsonProperty(value = "created_at")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt,

        @JsonProperty(value = "updated_at")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime updatedAt,

        @JsonProperty(value = "repeating")
        Boolean repeating,

        @JsonProperty(value = "student_id")
        @NotNull
        Long studentId,

        @JsonProperty(value = "year_id")
        @NotNull
        Long yearId,

        @JsonProperty(value = "cls_id")
        Long classLevelSubId,

        @JsonProperty(value = "number_of_subjects")
        Integer numberOfSubjects
) implements Serializable { }
