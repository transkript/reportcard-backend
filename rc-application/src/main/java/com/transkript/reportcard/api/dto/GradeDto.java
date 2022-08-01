package com.transkript.reportcard.api.dto;

import com.transkript.reportcard.data.enums.GradeDesc;

import java.io.Serializable;

public record GradeDto(GradeKeyDto key, Float score, GradeDesc description, Long sequenceId,
                       Long registrationId) implements Serializable {
    public record GradeKeyDto(Long sequenceId, Long registrationId) implements Serializable {
    }
}
