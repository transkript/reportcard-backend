package com.transkript.reportcard.data.entity;

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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject_registration")
public class SubjectRegistration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @ToString.Exclude
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sat_id", nullable = false)
    private StudentApplicationTrial studentApplicationTrial;

    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude @Builder.Default
    private Set<Grade> grades = new LinkedHashSet<>();

}
