package com.transkript.reportcard.business.mapper;


@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface StudentApplicationMapper {

	StudentApplicationDto mapStudentApplicationToDto(StudentApplication studentapplication);

	StudentApplication mapDtoToStudentApplication(StudentApplicationDto studentapplicationDto);

}
