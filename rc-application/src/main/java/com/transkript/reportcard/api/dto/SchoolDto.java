package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

public record SchoolDto (
        @JsonProperty(value = "id") Long id,
        @JsonProperty(value = "name") String name,
        @JsonProperty(value = "number_of_sections") Integer numberOfSections
) implements Serializable { }
