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
        actualTerm.setAcademicYear(academicYear);
        TermDto expectedTermDto = termMapper.mapTermToDto(actualTerm);

        Assertions.assertEquals(expectedTermDto.getId(), actualTerm.getId());
        Assertions.assertEquals(expectedTermDto.getName(), actualTerm.getName());
        Assertions.assertEquals(expectedTermDto.getAcademicYearId(), actualTerm.getAcademicYear().getId());
    }

    @Test
    void mapDtoToTerm() {
        TermDto actualTermDto = TestDefaults.TERM_DTO;
        Term expectedTerm = termMapper.mapDtoToTerm(actualTermDto);

        Assertions.assertEquals(expectedTerm.getId(), actualTermDto.getId());
        Assertions.assertEquals(expectedTerm.getName(), actualTermDto.getName());
    }
}
