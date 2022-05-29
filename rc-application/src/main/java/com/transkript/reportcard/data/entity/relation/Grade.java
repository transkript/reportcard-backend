package com.transkript.reportcard.data.entity.relation;


import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import com.transkript.reportcard.data.entity.composite.GradeKey;
import com.transkript.reportcard.data.enums.GradeDesc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "grade")
public class Grade {
    @EmbeddedId
    private GradeKey gradeKey;

    @Column(nullable = false, name = "grade_score")
    @Builder.Default
    private Float score = 0F;

    @Column(nullable = false, name = "grade_desc")
    @Enumerated(EnumType.STRING)
    private GradeDesc description;

    @MapsId("sequenceId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "sequence_id", nullable = false)
    private Sequence sequence;

    @MapsId("registrationId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_registration_ID", nullable = false)
    private SubjectRegistration subjectRegistration;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        if(score < 10) {
            description = GradeDesc.FAILED;
        } else if (score > 10) {
            description = GradeDesc.PASSED;
        } else if (score == 10) {
            description = GradeDesc.AVERAGE;
        } else {
            score = null;
            description = GradeDesc.NOT_GRADED;
        }
    }
}
