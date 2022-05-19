package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GradeDto {
    @JsonProperty(value = "score")
    private Float score;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "registration_id")
    private Long registrationId;

    @JsonProperty(value = "sequence_id")
    private Long sequenceId;
}
