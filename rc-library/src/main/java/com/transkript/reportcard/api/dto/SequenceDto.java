package com.transkript.reportcard.api.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SequenceDto {
    private Long id;
    private String name;
    private String termName;
}
