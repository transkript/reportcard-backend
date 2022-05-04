package com.transkript.reportcard.data.entity;


import com.transkript.reportcard.data.entity.composite.GradeId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "grade")
public class Grade {
	@EmbeddedId
	private GradeId id;

	@Column(nullable = false, name = "grade_score")
	private float score = 0;

	@Column(nullable = false, name = "grade_desc")
	private String description;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@MapsId("registrationId")
	@JoinColumn(name = "registrationId", nullable = false)
	private SubjectRegistration subjectRegistration;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@MapsId("sequenceId")
	@JoinColumn(name = "sequence_id", nullable = false)
	private Sequence sequence;

}
