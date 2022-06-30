package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface SubjectRegistrationMapper {

    @Mappings({
            @Mapping(target = "subjectId", expression = "java(subjectRegistration.getSubject().getId())"),
            @Mapping(target = "satId", expression = "java(subjectRegistration.getStudentApplicationTrial().getId())"),
    })
    SubjectRegistrationDto mapSubjectRegistrationToDto(SubjectRegistration subjectRegistration);

    @Mappings({
            @Mapping(target = "studentApplicationTrial", ignore = true),
            @Mapping(target = "subject", ignore = true),
            @Mapping(target = "grades", ignore = true),
    })
    @InheritInverseConfiguration
    SubjectRegistration mapDtoToSubjectRegistration(SubjectRegistrationDto subjectRegistrationDto);

}
