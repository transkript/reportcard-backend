package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GradeDto {
    @JsonProperty(value = "score")
    @Max(value = 20)
    @Min(value = 0)
    private Float score;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "registration_id")
    private Long registrationId;

    @JsonProperty(value = "sequence_id")
    private Long sequenceId;
}
