package com.transkript.reportcard.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.transkript.reportcard.api.dto.ClassLevelSubDto;
import com.transkript.reportcard.api.dto.GradeDto;
import com.transkript.reportcard.api.dto.SubjectDto;

import java.io.Serializable;
import java.util.Map;

public record ClassListResponse(
        @JsonProperty(value = "class_name") String className,
        @JsonProperty(value = "sequence_name") String sequenceName,
        @JsonProperty(value = "subject") SubjectDto subject,
        @JsonProperty(value = "class_level") ClassLevelSubDto classLevelSub,
        @JsonProperty(value = "student_grades") Map<Long, GradeDto> grades
) implements Serializable {
}
