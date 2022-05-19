package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.SchoolDto;
import com.transkript.reportcard.data.entity.School;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SchoolMapperTest {
    private final SchoolMapper schoolMapper;
    School testSchool;
    SchoolDto testSchoolDto;

    @Autowired
    SchoolMapperTest(SchoolMapper schoolMapper) {
        this.schoolMapper = schoolMapper;
    }

    @Test
    void mapSchoolToDto() {
        testSchool = School.builder()
                .id(1L)
                .name("DBA")
                .sections(List.of())
                .build();
        SchoolDto expectedDto = this.schoolMapper.mapSchoolToDto(testSchool);

        Assertions.assertEquals(expectedDto.getId(), testSchool.getId());
        Assertions.assertEquals(expectedDto.getName(), testSchool.getName());
        Assertions.assertEquals(expectedDto.getNumberOfSections(), testSchool.getSections().size());
    }

    @Test
    void mapDtoToSchool() {
        testSchoolDto = SchoolDto.builder()
                .id(2L)
                .name("ABC").build();
        School expected = this.schoolMapper.mapDtoToSchool(testSchoolDto);

        Assertions.assertEquals(expected.getId(), testSchoolDto.getId());
        Assertions.assertEquals(expected.getName(), testSchoolDto.getName());
    }
}
