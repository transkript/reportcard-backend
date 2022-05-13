package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.GradeDto;
import com.transkript.reportcard.data.entity.Grade;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface GradeMapper {

	@Mappings({
			@Mapping(target = "subjectRegistrationId", expression = "java(grade.getSubjectRegistration().getId())"),
			@Mapping(target = "sequenceId", expression = "java(grade.getSequence().getId())")
	})
	GradeDto mapGradeToDto(Grade grade);

	@Mappings({
			@Mapping(target = "subjectRegistration", ignore = true),
			@Mapping(target = "sequence", ignore = true)
	})
	@InheritInverseConfiguration
	Grade mapDtoToGrade(GradeDto gradeDto);

}
