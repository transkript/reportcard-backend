package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.SchoolDto;
import com.transkript.reportcard.data.entity.School;
import com.transkript.reportcard.data.entity.Section;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface SchoolMapper {
    @Mapping(target = "numberOfSections", expression = "java(mapNumberOfSections(school.getSections()))")
    @Mapping(source = "school.currentYear.id", target = "currentYearId")
    @Mapping(source = "school.currentSequence.id", target = "currentSequenceId")
    SchoolDto mapSchoolToDto(School school);

    default Integer mapNumberOfSections(List<Section> sections) {
        return sections.size();
    }

    @Mapping(target = "sections", ignore = true)
    @Mapping(target = "currentYear", ignore = true)
    @Mapping(target = "currentSequence", ignore = true)
    @InheritInverseConfiguration
    School mapDtoToSchool(SchoolDto schoolDto);

}
