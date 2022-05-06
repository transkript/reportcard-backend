package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.SchoolDto;
import com.transkript.reportcard.data.entity.School;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SchoolMapperTest {
    School testSchool;
    SchoolDto testSchoolDto;

    private final SchoolMapper schoolMapper;

    @Autowired
    SchoolMapperTest(SchoolMapper schoolMapper) {
        this.schoolMapper = schoolMapper;
    }

    @BeforeEach
    void setUp() {
        testSchool = School.builder()
                .id(1L)
                .name("DBA")
                .sections(List.of())
                .build();

        testSchoolDto = SchoolDto.builder()
                .id(2L)
                .name("ABC").build();
    }

    @Test
    void mapSchoolToDto() {
        SchoolDto expectedDto = this.schoolMapper.mapSchoolToDto(testSchool);

        Assertions.assertEquals(expectedDto.getId(), testSchool.getId());
    }

    @Test
    void mapDtoToSchool() {
        School expected = this.schoolMapper.mapDtoToSchool(testSchoolDto);

        Assertions.assertEquals(expected.getId(), testSchoolDto.getId());
    }
}