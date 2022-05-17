package com.transkript.reportcard.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicYearDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    //from relations
    @JsonProperty("number_of_terms")
    private Integer numberOfTerms;
}
