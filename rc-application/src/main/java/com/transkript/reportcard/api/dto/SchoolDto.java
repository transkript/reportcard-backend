package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public record SchoolDto(
        @JsonProperty(value = "id") Long id,
        @JsonProperty(value = "name") String name,
        @NotNull @JsonProperty("curr_year_id") Long currentYearId,
        @JsonProperty("curr_term") String currentTerm,
        @NotNull @JsonProperty("curr_seq_id") Long currentSequenceId,
        @NotNull @Max(value = 100) @JsonProperty("max_grade") Long maxGrade,
        @NotNull @JsonProperty("application_open") Boolean applicationOpen,
        @JsonProperty(value = "number_of_sections") Integer numberOfSections
) implements Serializable {
}
