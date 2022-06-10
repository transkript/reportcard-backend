package com.transkript.reportcard.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

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
    private Boolean applicationOpen;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "academic_year_id")
    private AcademicYear currentAcademicYear;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "term_id")
    private Term currentTerm;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "sequence_id")
    private Sequence currentSequence;

    @Builder.Default
    @Column(name = "max_grade_score", nullable = false)
    private Long maxGradeScore = 20L;

    @Builder.Default
    @Column(name = "min_grade_score", nullable = false)
    private Long minGradeScore = 0L;

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class AcademicInfo {
        @Column(name = "start_date")
        private LocalDateTime startDate;

        @Column(name = "end_date")
        private LocalDateTime endDate;
    }
}
