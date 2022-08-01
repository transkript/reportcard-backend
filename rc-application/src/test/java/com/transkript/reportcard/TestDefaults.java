package com.transkript.reportcard;

import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.api.dto.SequenceDto;
import com.transkript.reportcard.api.dto.SubjectDto;
import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.api.dto.TermDto;
import com.transkript.reportcard.data.entity.Section;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.composite.SubjectRegistrationKey;
import com.transkript.reportcard.data.entity.relation.SubjectRegistration;
import com.transkript.reportcard.data.entity.Term;

import java.time.LocalDateTime;
import java.util.List;

public class TestDefaults {
    public static final SequenceDto SEQUENCE_DTO = new SequenceDto(2L, "Second", 1L);
    /**
     * TODO Add actual object in place of all single relations
     */
    public static Subject SUBJECT = Subject.builder().id(1L).name("Math").code("MATHCODE").section(null).build();
    public static SubjectDto SUBJECT_DTO = new SubjectDto(1L, "Math Dto", 3, "MAT", 2L);

    public static SubjectRegistration SUBJECT_REGISTRATION = SubjectRegistration.builder()
            .key(new SubjectRegistrationKey(1L, 2L))
            .createdAt(LocalDateTime.now()).subject(SUBJECT).grades(List.of()).build();
    public static SubjectRegistrationDto SUBJECT_REGISTRATION_DTO = null;

    public static Section SECTION = null;
    public static SectionDto SECTION_DTO = null;

    public static Term TERM = Term.builder().id(1L).name("Term").sequences(List.of()).build();
    public static TermDto TERM_DTO = new TermDto(1L, "TermDto");
}
