package com.transkript.reportcard.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public record StudentApplicationRequest(
        @NotNull @JsonProperty(value = "class_id") Long classSubId,
        @NotNull @JsonProperty(value = "year_id") Long yearId,
        @NotNull @JsonProperty(value = "student_id") Long studentId
) implements Serializable {

}
