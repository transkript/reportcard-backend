package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.data.entity.School;
import com.transkript.reportcard.data.entity.Section;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SectionMapperTest {
    Section testSection;
    School testSchool;
    SectionDto testSectionDto;

    private final SectionMapper sectionMapper;

    @Autowired
    SectionMapperTest(SectionMapper sectionMapper) {
        this.sectionMapper = sectionMapper;
    }

    @BeforeEach
    void setUp() {
        testSchool = School.builder()
                .id(1L)
                .name("DBA")
                .sections(List.of())
                .build();

        testSection = Section.builder()
                .id(1L)
                .name("English")
                .category("Grammar")
                .classLevels(List.of())
                .school(testSchool)
                .subjects(List.of())
                .build();

        testSectionDto = SectionDto.builder()
                .id(2L)
                .name("French")
                .category("Technical")
                .build();
    }

    @Test
    void mapSectionToDto() {
        SectionDto expectedDto = this.sectionMapper.mapSectionToDto(testSection);

        Assertions.assertEquals(expectedDto.getId(), testSection.getId());
        Assertions.assertEquals(expectedDto.getSchoolName(), testSection.getSchool().getName());
    }

    @Test
    void mapDtoToSection() {
        Section expectedSection = this.sectionMapper.mapDtoToSection(testSectionDto);

        Assertions.assertEquals(expectedSection.getId(), testSectionDto.getId());
    }
}