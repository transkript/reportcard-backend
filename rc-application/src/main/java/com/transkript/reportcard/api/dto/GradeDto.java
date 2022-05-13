package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradeDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("score")
    private Float score;

    @JsonProperty("description")
    private String description;

    @JsonProperty("subject_reg_id")
    private Long subjectRegistrationId;

    @JsonProperty("sequence_id")
    private Long sequenceId;
}
