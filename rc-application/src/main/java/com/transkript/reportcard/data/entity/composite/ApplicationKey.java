package com.transkript.reportcard.data.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationKey implements Serializable {
    @JoinColumn(name = "student_id") Long studentId;
    @JoinColumn(name = "class_level_sub_id") Long classSubId;
}
