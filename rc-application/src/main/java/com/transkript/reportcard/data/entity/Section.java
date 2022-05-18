package com.transkript.reportcard.data.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "section")
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "category", nullable = false)
	private String category;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
    private List<ClassLevel> classLevels = new ArrayList<>();

	@ManyToOne(optional = false)
	@JoinColumn(name = "school_id", nullable = false)
	private School school;

	@OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<Subject> subjects = new ArrayList<>();

}
