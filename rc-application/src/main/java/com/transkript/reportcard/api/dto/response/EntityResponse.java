package com.transkript.reportcard.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.util.Map;

@Builder
public record EntityResponse(
        @JsonProperty("id") Long id,
        @JsonProperty(value = "ids") Map<String, Object> ids,
        @JsonProperty(value = "message") String message,
        @JsonProperty(value = "entity_name") String entityName,
        @JsonProperty(value = "status") Integer status,
        @JsonProperty(value = "log") Boolean log,
        @JsonProperty(value = "severity") String severity
) implements Serializable {
}
