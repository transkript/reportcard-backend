package com.transkript.reportcard.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public record ClassListRequest(
        @NotNull @JsonProperty(value = "year_id") Long academicYearId,
        @NotNull @JsonProperty(value = "class_id") Long classId,
        @NotNull @JsonProperty(value = "subject_id") Long subjectId,
        @NotNull @JsonProperty(value = "sequence_id") Long sequenceId
) implements Serializable {

}
