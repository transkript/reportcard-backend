package com.transkript.reportcard.data.entity.relation;


import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.composite.GradeKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
    private String description;

    @ManyToOne(optional = false)
    @MapsId("sequenceId")
    @JoinColumn(name = "sequence_id", nullable = false)
    private Sequence sequence;

    @MapsId("registrationId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_registration_ID", nullable = false)
    private SubjectRegistration subjectRegistration;


}
