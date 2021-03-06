package com.transkript.reportcard.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.api.dto.StudentDto;
import com.transkript.reportcard.data.entity.relation.StudentApplicationTrial;

import java.io.Serializable;
import java.util.List;

public record StudentApplicationResponse(
        @JsonProperty(value = "class_name") String className,
        @JsonProperty(value = "student") StudentDto student,
        @JsonProperty(value = "application") StudentApplicationDto application,
        @JsonProperty(value = "application_trials") List<StudentApplicationTrial> studentApplicationTrials
) implements Serializable {
}
