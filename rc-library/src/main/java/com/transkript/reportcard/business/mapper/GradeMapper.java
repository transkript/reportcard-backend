package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.GradeDto;
import com.transkript.reportcard.data.entity.Grade;
import org.mapstruct.Mapper;

// @Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface GradeMapper {

	GradeDto mapGradeToDto(Grade grade);

	Grade mapDtoToGrade(GradeDto gradeDto);

}
