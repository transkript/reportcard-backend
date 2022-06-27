package com.transkript.reportcard.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public record ReportCardRequest(
        @NotNull @JsonProperty(value = "student_id") Long satId,
        @NotNull @JsonProperty(value = "sequences") SequenceRequest sequenceRequest
) implements Serializable {
    public record SequenceRequest(
            @JsonProperty(value = "x_sequence") Long openingSequenceId,
            @JsonProperty(value = "y_sequence") Long closingSequenceId
    ) implements Serializable {
    }
}


