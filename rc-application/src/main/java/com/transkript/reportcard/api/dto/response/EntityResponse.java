package com.transkript.reportcard.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record EntityResponse(
        @JsonProperty("id") Object id,
        @JsonProperty(value = "message") String message,
        @JsonProperty(value = "log") Boolean log
) implements Serializable {
}
