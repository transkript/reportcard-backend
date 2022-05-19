package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.data.entity.relation.SubjectRegistration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface SubjectRegistrationMapper {

    @Mappings({
            @Mapping(target = "applicationId", expression = "java(subjectRegistration.getStudentApplication().getId())"),
            @Mapping(target = "subjectId", expression = "java(subjectRegistration.getSubject().getId())"),
    })
    SubjectRegistrationDto mapSubjectRegistrationToDto(SubjectRegistration subjectRegistration);

    @Mappings({
            @Mapping(target = "studentApplication", ignore = true),
            @Mapping(target = "subject", ignore = true),
            @Mapping(target = "grades", ignore = true),
    })
    @InheritInverseConfiguration
    SubjectRegistration mapDtoToSubjectRegistration(SubjectRegistrationDto subjectRegistrationDto);

}
