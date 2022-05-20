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
            @Mapping(target = "studentId", expression = "java(application.getStudent().getId())"),
            @Mapping(target = "academicYearId", expression = "java(application.getAcademicYear().getId())"),
            @Mapping(target = "numberOfSubjects", expression = "java(application.getSubjectRegistrations().size())"),
            @Mapping(target = "classLevelId", expression = "java(application.getClassLevel().getId())"),
            @Mapping(target = "classLevelSubId", expression = "java(application.getClassLevelSub().getId())"),
    })
    StudentApplicationDto mapStudentApplicationToDto(StudentApplication application);

    @Mappings({
            @Mapping(target = "student", ignore = true),
            @Mapping(target = "academicYear", ignore = true),
            @Mapping(target = "subjectRegistrations", ignore = true),
            @Mapping(target = "classLevel", ignore = true),
            @Mapping(target = "classLevelSub", ignore = true),
    })
    @InheritInverseConfiguration
    StudentApplication mapDtoToStudentApplication(StudentApplicationDto studentapplicationDto);

}
