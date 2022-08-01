package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public record SchoolDto(
        Long id,
        String name,
        Long currYearId,
        String currTerm,
        Long currSeqId,
        Long maxGrade,
        Boolean applicationsOpen,
        Integer numberOfSections
) implements Serializable {
}
