package com.transkript.reportcard.business.mapper;


@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface SchoolMapper {

	SchoolDto mapSchoolToDto(School school);

	School mapDtoToSchool(SchoolDto schoolDto);

}
