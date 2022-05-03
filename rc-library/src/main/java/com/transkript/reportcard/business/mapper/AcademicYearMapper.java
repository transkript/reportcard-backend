package com.transkript.reportcard.business.mapper;


@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface AcademicYearMapper {

	AcademicYearDto mapAcademicYearToDto(AcademicYear academicyear);

	AcademicYear mapDtoToAcademicYear(AcademicYearDto academicyearDto);

}
