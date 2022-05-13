package com.transkript.reportcard.data.entity;


import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity @Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "academic_year")
public class AcademicYear {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "year_name")
	private String name;

	// one to many term
	@OneToMany(mappedBy = "academicYear", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Term> terms = new LinkedHashSet<>();

	@OneToMany(mappedBy = "academicYear", orphanRemoval = true)
	private List<StudentApplication> studentApplications = new ArrayList<>();

}
