package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
public record StudentDto(
        @NotNull Long id,
        @NotBlank String name,

        @Size(min = 1, max = 1, message = "gender should have just 1 character: M or m (Male), F or f (Female), O or o (Other)")
        @NotBlank() String gender,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime dob,
        @NotNull() String pob,
        String regNum,
        Long schoolId
) implements Serializable {
}
