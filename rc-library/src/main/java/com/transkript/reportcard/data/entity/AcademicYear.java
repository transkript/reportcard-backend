package com.transkript.reportcard.data.entity;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "academicyear")
public class AcademicYear {
	@Id	private Long academicyearId;
}
