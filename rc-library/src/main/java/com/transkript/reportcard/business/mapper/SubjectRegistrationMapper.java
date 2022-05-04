package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import org.mapstruct.Mapper;

// @Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface SubjectRegistrationMapper {

	SubjectRegistrationDto mapSubjectRegistrationToDto(SubjectRegistration subjectregistration);

	SubjectRegistration mapDtoToSubjectRegistration(SubjectRegistrationDto subjectregistrationDto);

}
