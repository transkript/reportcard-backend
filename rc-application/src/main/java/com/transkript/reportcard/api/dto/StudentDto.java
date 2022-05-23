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
import java.time.LocalDateTime;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    @NotBlank(message = "Name is required")
    private String name;

    @JsonProperty("gender")
    @NotBlank()
    @Size(min = 1, max = 1, message = "gender should have just 1 character: M or m (Male), F or f (Female), O or o (Other)")
    private String gender;

    @JsonProperty("date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dob;

    @JsonProperty("place_of_birth")
    @NotNull()
    private String pob;

    @JsonProperty("registration_number")
    @NotNull()
    private String regNum;

    // from relations
    @JsonProperty("number_of_applications")
    private Integer numberOfApplications;
}
