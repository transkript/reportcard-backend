package com.transkript.reportcard.business.mapper;


@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface GradeMapper {

	GradeDto mapGradeToDto(Grade grade);

	Grade mapDtoToGrade(GradeDto gradeDto);

}
