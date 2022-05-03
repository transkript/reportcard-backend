package com.transkript.reportcard.business.mapper;


@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface SubjectMapper {

	SubjectDto mapSubjectToDto(Subject subject);

	Subject mapDtoToSubject(SubjectDto subjectDto);

}
