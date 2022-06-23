package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
public record StudentDto (
        @JsonProperty("id")
        @NotNull
        Long id,

        @JsonProperty("name")
        @NotBlank
        String name,

        @JsonProperty("gender")
        @NotBlank()
        @Size(min = 1, max = 1, message = "gender should have just 1 character: M or m (Male), F or f (Female), O or o (Other)")
        String gender,

        @JsonProperty("dob")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime dob,

        @JsonProperty("pob")
        @NotNull()
        String pob,

        @JsonProperty("regno")
        String regNum
) implements Serializable { }
