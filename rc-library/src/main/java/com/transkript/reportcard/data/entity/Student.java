package com.transkript.reportcard.data.entity;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student {
	@Id	private Long studentId;
}
