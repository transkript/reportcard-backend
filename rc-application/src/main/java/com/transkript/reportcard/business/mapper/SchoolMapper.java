package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.SchoolDto;
import com.transkript.reportcard.data.entity.School;
import com.transkript.reportcard.data.entity.Section;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface SchoolMapper {
    @Mappings({
            @Mapping(
                    target = "numberOfSections",
                    expression = "java(mapNumberOfSections(school.getSections()))"
            )
    })
    SchoolDto mapSchoolToDto(School school);

    default Integer mapNumberOfSections(List<Section> sections) {
        return sections.size();
    }

    @Mappings({
            @Mapping(target = "sections", ignore = true)
    })
    @InheritInverseConfiguration
    School mapDtoToSchool(SchoolDto schoolDto);

}
