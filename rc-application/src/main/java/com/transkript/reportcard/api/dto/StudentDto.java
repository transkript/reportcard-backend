package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("date_of_birth")
    private LocalDateTime dob;

    @JsonProperty("place_of_birth")
    private String pob;

    @JsonProperty("registration_number")
    private String regNum;

    // from relations
    @JsonProperty("number_of_applications")
    private Integer numberOfApplications;
}
