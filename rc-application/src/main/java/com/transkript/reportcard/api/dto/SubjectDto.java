package com.transkript.reportcard.api.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public record SubjectDto(
        Long id,
        @NotNull @NotEmpty String name,
        @NotNull @Min(0) Integer coefficient,
        @NotNull @NotEmpty String code,
        @NotNull Long sectionId
) implements Serializable { }
