package com.transkript.reportcard.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "class_level_sub")
public class ClassLevelSub {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "sub_name")
	private String name;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "class_level_id", nullable = false)
	private ClassLevel classLevel;

}
