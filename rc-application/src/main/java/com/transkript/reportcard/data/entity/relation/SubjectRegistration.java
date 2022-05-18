package com.transkript.reportcard.data.entity.relation;


import com.transkript.reportcard.data.entity.Grade;
import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.composite.SubjectRegistrationKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject_registration")
public class SubjectRegistration {
	@EmbeddedId
	private SubjectRegistrationKey subjectRegistrationKey;

	@MapsId(value = "studentApplicationId")
	@ManyToOne(optional = false)
	@JoinColumn(name = "application_id", nullable = false)
	private StudentApplication studentApplication;

	@MapsId(value = "subjectId")
	@ManyToOne(optional = false)
	@JoinColumn(name = "subject_id", nullable = false)
	private Subject subject;

	@Builder.Default
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@Builder.Default
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt = LocalDateTime.now();


	@OneToMany(mappedBy = "subjectRegistration", orphanRemoval = true)
	private Set<Grade> grades = new LinkedHashSet<>();
}
