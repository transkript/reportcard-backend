package com.transkript.reportcard.data.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "gender", nullable = false)
	private String gender;

	@Column(name = "dob", nullable = false)
	private LocalDateTime dob;

	@Column(name = "pob", nullable = false)
	private String pob;

	@Column(name = "reg_num", nullable = false, unique = true)
	private String regNum;
}
