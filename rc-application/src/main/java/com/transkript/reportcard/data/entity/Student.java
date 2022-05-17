package com.transkript.reportcard.data.entity;


import com.transkript.reportcard.data.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false)
	private Gender gender;

	@Column(name = "dob", nullable = false)
	private LocalDateTime dob = LocalDateTime.now();

	@Column(name = "pob", nullable = false)
	private String pob;

	@Column(name = "reg_num", nullable = false, unique = true)
	private String regNum;

	@Builder.Default
	@OneToMany(mappedBy = "student", orphanRemoval = true)
	private List<StudentApplication> studentApplications = new ArrayList<>();

}
