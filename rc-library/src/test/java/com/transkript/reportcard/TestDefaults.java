package com.transkript.reportcard;

import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.api.dto.SubjectDto;
import com.transkript.reportcard.data.entity.Section;
import com.transkript.reportcard.data.entity.Subject;

public class TestDefaults {
    public static Subject SUBJECT = Subject.builder().id(1L).name("Math").code("MATHCODE").build();
    public static SubjectDto SUBJECT_DTO = SubjectDto.builder().id(1L).name("Math DTO").code("MATHCODEDTO").build();

    public static Section SECTION = null;
    public static SectionDto SECTION_DTO = null;
}
