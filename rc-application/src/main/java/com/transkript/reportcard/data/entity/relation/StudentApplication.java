package com.transkript.reportcard.data.entity.relation;


import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.entity.composite.ApplicationKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
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
@Table(name = "student_application")
public class StudentApplication {
    @EmbeddedId
    private ApplicationKey key;

    @MapsId("studentId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @MapsId("classSubId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "class_level_sub_id")
    private ClassLevelSub classLevelSub;

    @OneToMany(mappedBy = "studentApplication", orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<StudentApplicationTrial> studentApplicationTrials = new ArrayList<>();
}
