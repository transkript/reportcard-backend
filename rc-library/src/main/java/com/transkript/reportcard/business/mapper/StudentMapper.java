package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.StudentDto;
import com.transkript.reportcard.data.entity.Student;
import org.mapstruct.Mapper;

// @Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface StudentMapper {

	StudentDto mapStudentToDto(Student student);

	Student mapDtoToStudent(StudentDto studentDto);

}
