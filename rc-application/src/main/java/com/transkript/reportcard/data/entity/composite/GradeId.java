package com.transkript.reportcard.data.entity.composite;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class GradeId implements Serializable {
    private static final long serialVersionUID = -636799324474621700L;
    @Column(name = "student_id")
    private Long sequenceId;

    @Column(name = "registration_id")
    private Long registrationId;

}
