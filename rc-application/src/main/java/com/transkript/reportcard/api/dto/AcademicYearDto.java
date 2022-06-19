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
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    //from relations

    @JsonProperty(value = "start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @JsonProperty("academic_info")
    private AcademicInfoDto academicInfo;
}
