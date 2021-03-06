package com.transkript.reportcard.data.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationKey implements Serializable {
    private Long studentId;
    private Long classSubId;
}
