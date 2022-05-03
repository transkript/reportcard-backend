package com.transkript.reportcard.data.entity;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject")
public class Subject {
	@Id	private Long subjectId;
}
