package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.data.entity.StudentApplication;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface StudentApplicationMapper {
	@Mappings({
			@Mapping(
					target = "studentName",
					expression = "java(studentapplication.getStudent().getName())"
			),
			@Mapping(
					target = "academicYearName",
					expression = "java(studentapplication.getAcademicYear().getName())"
			),
			@Mapping(
					target = "numberOfSubjects",
					expression = "java(studentapplication.getSubjectRegistrations().size())"
			)

	})
	StudentApplicationDto mapStudentApplicationToDto(StudentApplication studentapplication);

	@Mappings({
			@Mapping(target = "student", ignore = true),
			@Mapping(target = "academicYear", ignore = true),
			@Mapping(target = "subjectRegistrations", ignore = true)
	})
	@InheritInverseConfiguration
	StudentApplication mapDtoToStudentApplication(StudentApplicationDto studentapplicationDto);

}
