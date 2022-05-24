package com.transkript.reportcard.data.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ApplicationKey implements Serializable {
    @JoinColumn(name = "student_id")
    Long studentId;

    @JoinColumn(name = "academic_year_id")
    Long yearId;
}
