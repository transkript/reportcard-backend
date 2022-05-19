package com.transkript.reportcard.data.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class GradeKey implements Serializable {
    @Serial
    private static final long serialVersionUID = -636799324474621700L;

    @Column(name = "student_id")
    private Long sequenceId;

    @Column(name = "registration_id")
    private Long registrationId;
}
