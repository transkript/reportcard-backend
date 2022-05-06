package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.AcademicYearDto;
import com.transkript.reportcard.data.entity.AcademicYear;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@SpringBootTest
class AcademicYearMapperTest {
    AcademicYear testacademicYear;
    AcademicYearDto testacademicYearDto;

    private final AcademicYearMapper academicYearMapper;

    @Autowired
    AcademicYearMapperTest(AcademicYearMapper academicYearMapper) {
        this.academicYearMapper = academicYearMapper;
    }


    @BeforeEach
    void setup() {
        testacademicYear = AcademicYear.builder().id(1L).name("2022").terms(Set.of()).build();
        testacademicYearDto = AcademicYearDto.builder().id(2L).name("2022").build();
    }

    @Test
    void mapAcademicYearToDto() {
        AcademicYearDto expectedDto = this.academicYearMapper.mapAcademicYearToDto(testacademicYear);

        Assertions.assertEquals(expectedDto.getId(), testacademicYear.getId());
    }
    @Test
    void mapDtoToAcademicYear() {
    }
}