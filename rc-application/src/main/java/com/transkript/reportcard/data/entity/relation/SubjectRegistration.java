package com.transkript.reportcard.data.entity.relation;

import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.composite.SubjectRegistrationKey;
import com.transkript.reportcard.data.entity.relation.Grade;
import com.transkript.reportcard.data.entity.relation.StudentApplicationTrial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject_registration")
public class SubjectRegistration {
    @EmbeddedId
    private SubjectRegistrationKey key;

    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @ToString.Exclude
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();



    @MapsId("subjectId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @MapsId("satId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "student_application_trial_id", nullable = false)
    private StudentApplicationTrial studentApplicationTrial;

    @OneToMany(mappedBy = "subjectRegistration", orphanRemoval = true)
    @ToString.Exclude
    private List<Grade> grades = new ArrayList<>();

}
