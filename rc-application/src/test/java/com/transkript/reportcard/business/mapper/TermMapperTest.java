package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.TestDefaults;
import com.transkript.reportcard.api.dto.TermDto;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.Term;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TermMapperTest {
    private final TermMapper termMapper;

    @Autowired
    public TermMapperTest(TermMapper termMapper) {
        this.termMapper = termMapper;
    }

    @Test
    void mapTermToDto() {
        Term actualTerm = TestDefaults.TERM;
        AcademicYear academicYear = new AcademicYear();
        academicYear.setId(1L);
        TermDto expectedTermDto = termMapper.mapTermToDto(actualTerm);

        Assertions.assertEquals(expectedTermDto.id(), actualTerm.getId());
        Assertions.assertEquals(expectedTermDto.name(), actualTerm.getName());

    }

    @Test
    void mapDtoToTerm() {
        TermDto actualTermDto = TestDefaults.TERM_DTO;
        Term expectedTerm = termMapper.mapDtoToTerm(actualTermDto);

        Assertions.assertEquals(expectedTerm.getId(), actualTermDto.id());
        Assertions.assertEquals(expectedTerm.getName(), actualTermDto.name());
    }
}
