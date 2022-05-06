package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter
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
    private Integer numberOfClassLevels;
    private String schoolName;
    private Integer numberOfSubjects;
}
