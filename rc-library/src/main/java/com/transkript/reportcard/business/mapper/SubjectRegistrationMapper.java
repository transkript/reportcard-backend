package com.transkript.reportcard.business.mapper;


@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface SubjectRegistrationMapper {

	SubjectRegistrationDto mapSubjectRegistrationToDto(SubjectRegistration subjectregistration);

	SubjectRegistration mapDtoToSubjectRegistration(SubjectRegistrationDto subjectregistrationDto);

}
