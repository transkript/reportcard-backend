package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public record SequenceDto (
        @JsonProperty(value = "id")Long id,
        @NotNull @JsonProperty(value = "name") String name,
        @NotNull @JsonProperty(value = "term_id") Long termId)
implements Serializable { }
