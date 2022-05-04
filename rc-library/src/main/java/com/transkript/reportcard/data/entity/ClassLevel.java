package com.transkript.reportcard.data.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "class_level")
public class ClassLevel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

    @OneToMany(mappedBy = "classLevel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClassLevelSub> classLevelSubs = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "section_id", nullable = false)
	private Section section;

}
