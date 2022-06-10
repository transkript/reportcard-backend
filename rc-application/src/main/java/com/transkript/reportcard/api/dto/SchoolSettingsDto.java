package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

public record SchoolSettingsDto (
        @JsonProperty("id") Long id,
        @JsonProperty("curr_year_id") Long currentYearId,
        @JsonProperty("curr_term_id") Long currentTermId,
        @JsonProperty("curr_seq_id") Long currentSequenceId,
        @Min(value = 0) @JsonProperty("min_grade") Long minGrade,
        @Max(value = 100)@JsonProperty("max_grade") Long maxGrade,
        @JsonProperty("application_is_open") Boolean applicationOpen
)
implements Serializable {}
