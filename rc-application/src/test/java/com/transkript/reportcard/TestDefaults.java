package com.transkript.reportcard;

import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.api.dto.SequenceDto;
import com.transkript.reportcard.api.dto.SubjectDto;
import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.api.dto.TermDto;
import com.transkript.reportcard.data.entity.Section;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import com.transkript.reportcard.data.entity.Term;

import java.time.LocalDateTime;
import java.util.List;

public class TestDefaults {
    public static final SequenceDto SEQUENCE_DTO = new SequenceDto(2L, "Second", 1L);
    /**
     * TODO Add actual object in place of all single relations
     */
    public static Subject SUBJECT = Subject.builder().id(1L).name("Math").code("MATHCODE").section(null).build();
    public static SubjectDto SUBJECT_DTO = SubjectDto.builder().id(1L).name("Math DTO").code("MATHCODEDTO").build();

    public static SubjectRegistration SUBJECT_REGISTRATION = SubjectRegistration.builder()
            .id(1L)
            .createdAt(LocalDateTime.now()).subject(SUBJECT).grades(List.of()).build();
    public static SubjectRegistrationDto SUBJECT_REGISTRATION_DTO = SubjectRegistrationDto.builder()
            .createdAt(LocalDateTime.now()).subjectId(1L).build();

    public static Section SECTION = null;
    public static SectionDto SECTION_DTO = null;

    public static Term TERM = Term.builder().id(1L).name("Term").sequences(List.of()).build();
    public static TermDto TERM_DTO = new TermDto(1L, "TermDto");
}
