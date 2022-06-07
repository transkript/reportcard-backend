package com.transkript.reportcard.data.entity.extra;

import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.Term;
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

    @Column(name = "school_name", nullable = false, length = 128)
    private String schoolName;

    @Column
    private Boolean applicationOpen;

    @Column
    private String regNoPattern;

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
}
