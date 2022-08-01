package com.transkript.reportcard.data.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRegistrationKey implements Serializable {
    @Serial
    private static final long serialVersionUID = -7726739515805667721L;
    private Long subjectId;
    private Long satId;
}
