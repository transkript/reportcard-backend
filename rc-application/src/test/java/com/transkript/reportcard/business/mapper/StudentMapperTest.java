package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.StudentDto;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
class StudentMapperTest {
    private final StudentMapper studentMapper;

    @Autowired
    public StudentMapperTest(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Test
    void mapStudentToDto() {
        Student actualStudent = Student.builder().id(1L).name("test student")
                .pob("test pob").regNum("REGNO")
                .gender(Gender.MALE).dob(LocalDateTime.now())
                .studentApplications(List.of()).build();
        StudentDto expectedDto = this.studentMapper.mapStudentToDto(actualStudent);

        Assertions.assertEquals(expectedDto.id(), actualStudent.getId());
        Assertions.assertEquals(expectedDto.name(), actualStudent.getName());
        Assertions.assertEquals(expectedDto.pob(), actualStudent.getPob());
        Assertions.assertEquals(expectedDto.regNum(), actualStudent.getRegNum());
        Assertions.assertEquals(expectedDto.gender(), actualStudent.getGender().name().split("")[0]);
        Assertions.assertEquals(expectedDto.dob(), actualStudent.getDob());
    }

    @Test
    void mapDtoToStudent() {
        StudentDto actualStudentDto = StudentDto.builder().id(2L).name("test student dto").gender("F")
                .dob(LocalDateTime.now()).pob("test pob dto").regNum("REGNODTO")
                .build();

        Student expectedStudent = this.studentMapper.mapDtoToStudent(actualStudentDto);

        Assertions.assertEquals(expectedStudent.getId(), actualStudentDto.id());
        Assertions.assertEquals(expectedStudent.getName(), actualStudentDto.name());
        Assertions.assertEquals(expectedStudent.getPob(), actualStudentDto.pob());
        Assertions.assertEquals(expectedStudent.getRegNum(), actualStudentDto.regNum());
        Assertions.assertEquals(expectedStudent.getGender().name().split("")[0], actualStudentDto.gender());
        Assertions.assertEquals(expectedStudent.getDob(), actualStudentDto.dob());
    }
}
