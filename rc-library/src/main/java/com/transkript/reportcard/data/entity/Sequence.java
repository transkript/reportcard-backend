package com.transkript.reportcard.data.entity;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sequence")
public class Sequence {
	@Id	private Long sequenceId;
}
