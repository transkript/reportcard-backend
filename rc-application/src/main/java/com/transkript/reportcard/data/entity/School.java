package com.transkript.reportcard.data.entity;


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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column
    private Boolean applicationOpen;

    @OneToOne()
    @JoinColumn(name = "academic_year_id")
    private AcademicYear currentYear;

    @Column(name = "current_term")
    private String currentTerm;

    @OneToOne()
    @JoinColumn(name = "sequence_id")
    private Sequence currentSequence;

    @Builder.Default
    @Column(name = "max_grade_score", nullable = false)
    private Long maxGrade = 20L;


    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Section> sections = new ArrayList<>();

}
