package com.transkript.reportcard.data.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sequence")
public class Sequence {
	@Id
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "term_id", nullable = false)
	private Term term;

}
