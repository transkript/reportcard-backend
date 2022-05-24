package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.data.entity.StudentApplication;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface StudentApplicationMapper {
    @Mappings({
            @Mapping(target = "studentId", expression = "java(application.getApplicationKey().getStudentId())"),
            @Mapping(target = "academicYearId", expression = "java(application.getApplicationKey().getYearId())"),
            @Mapping(target = "numberOfSubjects", expression = "java(application.getSubjectRegistrations().size())"),
            @Mapping(target = "classLevelSubId", expression = "java(application.getClassLevelSub().getId())"),
    })
    StudentApplicationDto mapStudentApplicationToDto(StudentApplication application);

    @Mappings({
            @Mapping(target = "applicationKey", ignore = true),
            @Mapping(target = "student", ignore = true),
            @Mapping(target = "academicYear", ignore = true),
            @Mapping(target = "subjectRegistrations", ignore = true),
            @Mapping(target = "classLevelSub", ignore = true),
    })
    @InheritInverseConfiguration
    StudentApplication mapDtoToStudentApplication(StudentApplicationDto studentapplicationDto);

}
