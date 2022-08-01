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
     private Long id;
     private String name;
     private String category;
    // From relations
    private Integer numberOfClassLevels;
    private Long schoolId;
    private Integer numberOfSubjects;
}
