package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionDto {
    @JsonProperty(namespace = "id")
    private Long id;

    @JsonProperty(namespace = "name")
    private String name;

    @JsonProperty(namespace = "category")
    private String category;

    // From relations
    @JsonProperty(namespace = "number_of_class_levels")
    private Integer numberOfClassLevels;

    @JsonProperty(namespace = "school_id")
    private Long schoolId;

    @JsonProperty(namespace = "number_of_subjects")
    private Integer numberOfSubjects;
}
