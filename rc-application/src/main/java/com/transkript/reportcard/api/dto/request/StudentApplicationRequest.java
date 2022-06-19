package com.transkript.reportcard.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public record StudentApplicationRequest(
        @NotNull @JsonProperty(value = "class_id") Long classId,
        @NotNull @JsonProperty(value = "year_id") Long yearId
) implements Serializable {

}
