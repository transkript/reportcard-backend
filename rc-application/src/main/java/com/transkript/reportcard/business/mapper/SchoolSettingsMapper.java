package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.SchoolSettingsDto;
import com.transkript.reportcard.data.entity.SchoolSettings;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface SchoolSettingsMapper {
    @Mappings({
            @Mapping(target = "currentYearId", expression = "java(schoolSettings.getCurrentAcademicYear().getId())"),
            @Mapping(target = "currentTerm", expression = "java(schoolSettings.getCurrentSequence().getTerm().getName())"),
            @Mapping(target = "currentSequenceId", expression = "java(schoolSettings.getCurrentSequence().getId())")
    })
    SchoolSettingsDto mapSchoolSettingsToDto(SchoolSettings schoolSettings);

    @Mappings({
            @Mapping(target = "currentTerm", ignore = true),
            @Mapping(target = "currentSequence", ignore = true),
            @Mapping(target = "currentAcademicYear", ignore = true),
    })
    @InheritInverseConfiguration
    SchoolSettings mapDtoToSchoolSettings(SchoolSettingsDto schoolSettingsDto);
}
