package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.StudentDto;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.enums.Gender;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface StudentMapper {

    @Mapping(target = "gender", expression = "java(mapGender(student.getGender()))")
    @Mapping(source = "school.id", target = "schoolId")
    StudentDto mapStudentToDto(Student student);

    default String mapGender(Gender gender) {
        return switch (gender) { case MALE -> "M"; case FEMALE -> "F"; default -> "O"; };
    }


    @InheritInverseConfiguration
    @Mapping(target = "school", ignore = true)
    @Mapping(target = "studentApplications", ignore = true)
    @Mapping(target = "gender", expression = "java(inverseMapGender(studentDto.gender()))")
    Student mapDtoToStudent(StudentDto studentDto);

    default Gender inverseMapGender(String gender) {
        return switch (gender.toUpperCase()) {
            case "M" -> Gender.MALE;
            case "F" -> Gender.FEMALE;
            default -> Gender.OTHER;
        };
    }
}
