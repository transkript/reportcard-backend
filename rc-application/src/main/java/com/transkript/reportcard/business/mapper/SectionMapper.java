package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.data.entity.Section;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface SectionMapper {
    @Mappings({
            @Mapping(
                    target = "numberOfClassLevels",
                    expression = "java(section.getClassLevels().size())"
            ),
            @Mapping(
                    target = "schoolId",
                    expression = "java(section.getSchool().getId())"
            ),
            @Mapping(
                    target = "numberOfSubjects",
                    expression = "java(section.getSubjects().size())"
            )
    })
    SectionDto mapSectionToDto(Section section);

    @Mappings({
            @Mapping(target = "classLevels", ignore = true),
            @Mapping(target = "school", ignore = true),
            @Mapping(target = "subjects", ignore = true)
    })
    @InheritInverseConfiguration
    Section mapDtoToSection(SectionDto sectionDto);

}
