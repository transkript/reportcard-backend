package com.transkript.reportcard.data.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

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
    private Long sequenceId;
    private Long registrationId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GradeKey gradeKey = (GradeKey) o;
        return sequenceId != null && Objects.equals(sequenceId, gradeKey.sequenceId)
                && registrationId != null && Objects.equals(registrationId, gradeKey.registrationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequenceId, registrationId);
    }
}
