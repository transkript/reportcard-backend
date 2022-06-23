package com.transkript.reportcard.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "school_settings")
public class SchoolSettings {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column
    private String schoolName;

    @Column
    private Boolean applicationOpen;

    @OneToOne()
    @JoinColumn(name = "academic_year_id")
    private AcademicYear currentAcademicYear;

    @Column(name = "current_term")
    private String currentTerm;

    @OneToOne()
    @JoinColumn(name = "sequence_id")
    private Sequence currentSequence;

    @Builder.Default
    @Column(name = "max_grade_score", nullable = false)
    private Long maxGrade = 20L;

    @Builder.Default
    @Column(name = "min_grade_score", nullable = false)
    private Long minGrade = 0L;
}
