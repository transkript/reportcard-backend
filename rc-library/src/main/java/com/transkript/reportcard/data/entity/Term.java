package com.transkript.reportcard.data.entity;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "term")
public class Term {
	@Id	private Long termId;
}
