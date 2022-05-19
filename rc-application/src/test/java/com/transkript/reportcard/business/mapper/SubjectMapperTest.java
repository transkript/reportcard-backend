package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.TestDefaults;
import com.transkript.reportcard.api.dto.SubjectDto;
import com.transkript.reportcard.data.entity.Section;
import com.transkript.reportcard.data.entity.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SubjectMapperTest {
    private final SubjectMapper subjectMapper;

    @Autowired
    public SubjectMapperTest(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    @Test
    void mapSubjectToDto() {
        Subject actualSubject = TestDefaults.SUBJECT;
        // TODO load from test defaults
        Section section = new Section();
        section.setId(1L);
        actualSubject.setSection(section);
        SubjectDto expectedDto = subjectMapper.mapSubjectToDto(actualSubject);

        Assertions.assertEquals(expectedDto.getId(), actualSubject.getId());
        Assertions.assertEquals(expectedDto.getName(), actualSubject.getName());
        Assertions.assertEquals(expectedDto.getCode(), actualSubject.getCode());
        Assertions.assertEquals(expectedDto.getSectionId(), actualSubject.getSection().getId());
    }

    @Test
    void mapDtoToSubject() {
        SubjectDto actualDto = TestDefaults.SUBJECT_DTO;
        Subject expectedSubject = subjectMapper.mapDtoToSubject(actualDto);

        Assertions.assertEquals(expectedSubject.getId(), actualDto.getId());
        Assertions.assertEquals(expectedSubject.getName(), actualDto.getName());
        Assertions.assertEquals(expectedSubject.getCode(), actualDto.getCode());
    }
}
