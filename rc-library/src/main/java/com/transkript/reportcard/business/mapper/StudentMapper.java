package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.StudentDto;
import com.transkript.reportcard.data.entity.Student;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface StudentMapper {

	@Mappings({
			@Mapping(target = "numberOfApplications", expression = "java(student.getStudentApplications().size())")
	})
	StudentDto mapStudentToDto(Student student);


	@Mappings({
			@Mapping(target = "studentApplications", ignore = true)
	})
	@InheritInverseConfiguration
	Student mapDtoToStudent(StudentDto studentDto);

}
