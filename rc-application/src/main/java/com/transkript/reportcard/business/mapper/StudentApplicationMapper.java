package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.data.entity.composite.ApplicationKey;
import com.transkript.reportcard.data.entity.relation.StudentApplication;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface StudentApplicationMapper {
    @Mappings({
            @Mapping(target = "applicationKeyDto", expression = "java(mapApplicationKey(application.getKey()))"),
    })
    StudentApplicationDto mapStudentApplicationToDto(StudentApplication application);

    default StudentApplicationDto.ApplicationKeyDto mapApplicationKey(ApplicationKey applicationKey) {
        return new StudentApplicationDto.ApplicationKeyDto(applicationKey.getStudentId(), applicationKey.getClassSubId());
    }

    @Mappings({
            @Mapping(target = "key", ignore = true),
            @Mapping(target = "student", ignore = true),
            @Mapping(target = "classLevelSub", ignore = true),
            @Mapping(target = "studentApplicationTrials", ignore = true),
    })
    @InheritInverseConfiguration
    StudentApplication mapDtoToStudentApplication(StudentApplicationDto studentapplicationDto);
}
