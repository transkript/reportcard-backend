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
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "category")
    private String category;

    // From relations
    @JsonProperty(value = "number_of_class_levels")
    private Integer numberOfClassLevels;

    @JsonProperty(value = "school_id")
    private Long schoolId;

    @JsonProperty(value = "number_of_subjects")
    private Integer numberOfSubjects;
}
