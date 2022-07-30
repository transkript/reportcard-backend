package com.transkript.reportcard.data.entity;


import com.transkript.reportcard.business.util.SchoolUtil;
import com.transkript.reportcard.data.entity.relation.StudentApplication;
import com.transkript.reportcard.data.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;
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
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "reg_num", nullable = false, unique = true)
    private String regNum;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Builder.Default
    @Column(name = "dob", nullable = false)
    private LocalDateTime dob = LocalDateTime.now();

    @Column(name = "pob", nullable = false)
    private String pob;

    @Builder.Default
    @OneToMany(mappedBy = "student", orphanRemoval = true)
    @ToString.Exclude
    private List<StudentApplication> studentApplications = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @PreUpdate
    public void preUpdate() {
        this.setRegNum(SchoolUtil.generateRegNo(this.id, school.getName()));
    }
}
