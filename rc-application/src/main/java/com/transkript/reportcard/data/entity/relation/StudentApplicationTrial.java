package com.transkript.reportcard.data.entity.relation;

import com.transkript.reportcard.data.entity.AcademicYear;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "student_application_trial")
public class StudentApplicationTrial {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "repeating", nullable = false) Boolean repeating;
    @Column(name = "order_number", nullable = false) private Integer order;

    @Builder.Default
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "sa_student_id", referencedColumnName = "student_id", nullable = false),
            @JoinColumn(name = "sa_class_level_sub_id", referencedColumnName = "class_level_sub_id", nullable = false)
    })
    private StudentApplication studentApplication;


    @OneToMany(mappedBy = "studentApplicationTrial", orphanRemoval = true)
    @ToString.Exclude @Builder.Default
    private List<SubjectRegistration> subjectRegistrations = new ArrayList<>();

}
