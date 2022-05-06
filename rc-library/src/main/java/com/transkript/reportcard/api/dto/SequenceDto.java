package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SequenceDto {
    @JsonProperty(namespace = "id")
    private Long id;

    @JsonProperty(namespace = "name")
    private String name;

    @JsonProperty(namespace = "term_name")
    private String termName;
}
