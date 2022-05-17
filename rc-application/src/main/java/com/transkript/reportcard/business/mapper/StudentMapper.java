package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.StudentDto;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.enums.Gender;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface StudentMapper {

	@Mappings({
			@Mapping(target = "numberOfApplications", expression = "java(student.getStudentApplications().size())"),
			@Mapping(target = "gender", expression = "java(mapGender(student.getGender()))")
	})
	StudentDto mapStudentToDto(Student student);

	default String mapGender(Gender gender) {
		return switch(gender) {
			case MALE -> "F";
			case FEMALE -> "M";
			default -> "O";
		};
	}

	@Mappings({
			@Mapping(target = "studentApplications", ignore = true),
			@Mapping(target = "gender", expression = "java(inverseMapGender(studentDto.getGender()))")
	})
	@InheritInverseConfiguration
	Student mapDtoToStudent(StudentDto studentDto);

	default Gender inverseMapGender(String gender) {
		return switch (gender.toUpperCase()) {
			case "M" -> Gender.MALE;
			case "F" -> Gender.FEMALE;
			default -> Gender.OTHER;
		};
	}
}
