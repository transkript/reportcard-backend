package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public record SequenceDto(
        @JsonProperty(value = "id") Long id,
        @NotNull @JsonProperty(value = "name") String name,
        @NotNull @JsonProperty(value = "term_id") Long termId)
        implements Serializable {
}
