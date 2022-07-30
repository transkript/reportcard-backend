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

        Assertions.assertEquals(expectedDto.id(), testSchool.getId());
        Assertions.assertEquals(expectedDto.name(), testSchool.getName());
        Assertions.assertEquals(expectedDto.numberOfSections(), testSchool.getSections().size());
    }

    @Test
    void mapDtoToSchool() {
        testSchoolDto = new SchoolDto(2L, "ABC", 1L, "", 1L, 20L, true, 0);
        School expected = this.schoolMapper.mapDtoToSchool(testSchoolDto);

        Assertions.assertEquals(expected.getId(), testSchoolDto.id());
        Assertions.assertEquals(expected.getName(), testSchoolDto.name());
    }
}
