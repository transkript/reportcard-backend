package com.transkript.reportcard.data.entity;


import com.transkript.reportcard.data.entity.composite.ApplicationKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "student_application")
public class StudentApplication {
    @EmbeddedId
    private ApplicationKey applicationKey;

    @Builder.Default
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, updatable = true)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @MapsId("studentId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @MapsId("yearId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    @ManyToOne(optional = false)
    @JoinColumn(name = "class_level_sub_id", nullable = false)
    private ClassLevelSub classLevelSub;

    @OneToMany(mappedBy = "studentApplication", orphanRemoval = true)
    @ToString.Exclude
    private List<SubjectRegistration> subjectRegistrations = new ArrayList<>();
    /*
    @Builder.Default
    @OneToMany(mappedBy = "studentApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubjectRegistration> subjectRegistrations = new ArrayList<>();

     */
}
