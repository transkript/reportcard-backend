package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface SubjectRegistrationMapper {

    @Mappings({
            @Mapping(target = "studentId", expression = "java(mapStudentId(subjectRegistration.getStudentApplication()))"),
            @Mapping(target = "yearId", expression = "java(mapYearId(subjectRegistration.getStudentApplication()))"),
            @Mapping(target = "subjectId", expression = "java(subjectRegistration.getSubject().getId())"),
    })
    SubjectRegistrationDto mapSubjectRegistrationToDto(SubjectRegistration subjectRegistration);

    default Long mapStudentId(StudentApplication application) {
        return application.getApplicationKey().getStudentId();
    }

    default Long mapYearId(StudentApplication application) {
        return application.getApplicationKey().getYearId();
    }

    @Mappings({
            @Mapping(target = "studentApplication", ignore = true),
            @Mapping(target = "subject", ignore = true),
            @Mapping(target = "grades", ignore = true),
    })
    @InheritInverseConfiguration
    SubjectRegistration mapDtoToSubjectRegistration(SubjectRegistrationDto subjectRegistrationDto);

}
