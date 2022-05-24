package com.transkript.reportcard.data.entity;


import com.transkript.reportcard.data.entity.relation.SubjectRegistration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "student_application")
public class StudentApplication {
    @EmbeddedId
    private ApplicationKey applicationKey;

    @Builder.Default
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, updatable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "academic_year_id", nullable = false)
    private AcademicYear academicYear;

    @Builder.Default
    @OneToMany(mappedBy = "studentApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubjectRegistration> subjectRegistrations = new ArrayList<>();

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "class_level_id", nullable = false)
    private ClassLevel classLevel;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "class_level_sub_id", nullable = false)
    private ClassLevelSub classLevelSub;

}
