package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolDto {
    @JsonProperty(namespace = "id")
    private Long id;
    @JsonProperty(namespace = "name")
    private String name;

    // From relations
    @JsonProperty(namespace = "number_of_sections")
    private Integer numberOfSections;
}
