package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public record SchoolSettingsDto (
        @NotNull @JsonProperty("id") Long id,
        @NotNull @Min(value = 0) @JsonProperty("school_id") Long schoolId,
        @NotNull @JsonProperty("school_name") String schoolName,
        @NotNull @JsonProperty("curr_year_id") Long currentYearId,
        @JsonProperty("curr_term") String currentTerm,
        @NotNull @JsonProperty("curr_seq_id") Long currentSequenceId,
        @NotNull @Min(value = 0) @JsonProperty("min_grade") Long minGrade,
        @NotNull @Max(value = 100)@JsonProperty("max_grade") Long maxGrade,
        @NotNull @JsonProperty("application_is_open") Boolean applicationOpen
)
implements Serializable {}
