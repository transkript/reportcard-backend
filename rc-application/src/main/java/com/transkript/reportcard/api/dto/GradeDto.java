package com.transkript.reportcard.api.dto;

import com.transkript.reportcard.data.enums.GradeDesc;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public record GradeDto(@NotNull GradeKeyDto key, Float score, GradeDesc description, Long sequenceId,
                       SubjectRegistrationDto.SubjectRegistrationKeyDto registrationKey) implements Serializable {
    public record GradeKeyDto(
            @NotNull Long sequenceId,
            @NotNull Long subjectId,
            @NotNull Long satId
    ) implements Serializable { }
}
