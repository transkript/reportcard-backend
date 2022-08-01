package com.transkript.reportcard.api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicYearDto {
    private Long id;

    private String name;

    //from relations
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    private AcademicInfoDto academicInfo;
}
