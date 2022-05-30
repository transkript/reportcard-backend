package com.transkript.reportcard.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.transkript.reportcard.api.dto.GradeDto;
import com.transkript.reportcard.api.dto.StudentDto;

import java.io.Serializable;

public record StudentGrade(
        @JsonProperty(value = "student") StudentDto studentDto,
        @JsonProperty(value = "grade") GradeDto grade
) implements Serializable {

}
