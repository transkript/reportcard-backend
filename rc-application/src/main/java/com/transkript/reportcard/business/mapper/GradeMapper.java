package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.GradeDto;
import com.transkript.reportcard.data.entity.composite.GradeKey;
import com.transkript.reportcard.data.entity.relation.Grade;
import com.transkript.reportcard.data.entity.relation.SubjectRegistration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface GradeMapper {

	@Mappings({
			@Mapping(target = "registrationId", expression = "java(mapRegistrationId(grade.getGradeKey()))"),
			@Mapping(target = "sequenceId", expression = "java(mapSequenceId(grade.getGradeKey()))"),
	})
	GradeDto mapGradeToDto(Grade grade);

	default Long mapRegistrationId(GradeKey gradeKey) {
		return gradeKey.getRegistrationId();
	}

	default Long mapSequenceId(GradeKey gradeKey) {
		return gradeKey.getSequenceId();
	}

	@Mappings({
			@Mapping(target = "subjectRegistration", ignore = true),
			@Mapping(target = "sequence", ignore = true),
			@Mapping(target = "gradeKey", expression = "java(inverseMapGradeKey(gradeDto))")
	})
	@InheritInverseConfiguration
	Grade mapDtoToGrade(GradeDto gradeDto);

	default GradeKey inverseMapGradeKey(GradeDto gradeDto) {
		return GradeKey.builder().sequenceId(gradeDto.getSequenceId())
				.registrationId(gradeDto.getRegistrationId())
				.build();
	}
}
