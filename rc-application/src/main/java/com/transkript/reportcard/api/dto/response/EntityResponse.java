package com.transkript.reportcard.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityResponse {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "entity_name")
    private String entityName;

    @Builder.Default
    @JsonProperty(value = "date")
    private Date date = new Date();
}
