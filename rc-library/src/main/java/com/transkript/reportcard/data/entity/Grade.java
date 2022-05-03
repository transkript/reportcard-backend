package com.transkript.reportcard.data.entity;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "grade")
public class Grade {
	@Id	private Long gradeId;
}
