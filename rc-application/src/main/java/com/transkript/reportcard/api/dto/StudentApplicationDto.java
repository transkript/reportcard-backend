package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public record StudentApplicationDto(
        @JsonProperty(value = "application_key") ApplicationKeyDto applicationKeyDto,
        @JsonProperty(value = "application_trials") List<StudentApplicationTrialDto> applicationTrialDtos
) implements Serializable {
    public record ApplicationKeyDto(
            @NotNull @JsonProperty(value = "student_id") Long studentId,
            @NotNull @JsonProperty(value = "class_sub_id") Long classSubId
    ) implements Serializable {
    }
}
