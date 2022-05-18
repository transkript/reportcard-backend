package com.transkript.reportcard.data.entity;


import com.transkript.reportcard.data.entity.relation.SubjectRegistration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject")
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "subject_name", nullable = false)
	private String name;

	@Column(name = "subject_coefficient", nullable = false)
	private Integer coefficient;

	@Column(name = "subject_code", nullable = false, unique = true)
	private String code;

	@ManyToOne(optional = false)
	@JoinColumn(name = "section_id", nullable = false)
	private Section section;

	@Builder.Default
	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
	private Set<SubjectRegistration> subjectRegistrations = new LinkedHashSet<>();

}
