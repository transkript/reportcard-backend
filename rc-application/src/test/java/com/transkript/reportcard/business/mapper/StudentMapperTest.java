package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.StudentDto;
import com.transkript.reportcard.data.entity.Student;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
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
                .gender("M").dob(LocalDateTime.now())
                .studentApplications(List.of()).build();
        StudentDto expectedDto = this.studentMapper.mapStudentToDto(actualStudent);

        Assertions.assertEquals(expectedDto.getId(), actualStudent.getId());
        Assertions.assertEquals(expectedDto.getName(), actualStudent.getName());
        Assertions.assertEquals(expectedDto.getPob(), actualStudent.getPob());
        Assertions.assertEquals(expectedDto.getRegNum(), actualStudent.getRegNum());
        Assertions.assertEquals(expectedDto.getGender(), actualStudent.getGender());
        Assertions.assertEquals(expectedDto.getDob(), actualStudent.getDob());
        Assertions.assertEquals(expectedDto.getNumberOfApplications(), actualStudent.getStudentApplications().size());
    }

    @Test
    void mapDtoToStudent() {
        StudentDto actualStudentDto = StudentDto.builder().id(2L).name("test student dto").gender("F")
                .dob(LocalDateTime.now()).pob("test pob dto").regNum("REGNODTO")
                .build();

        Student expectedStudent = this.studentMapper.mapDtoToStudent(actualStudentDto);

        Assertions.assertEquals(expectedStudent.getId(), actualStudentDto.getId());
        Assertions.assertEquals(expectedStudent.getName(), actualStudentDto.getName());
        Assertions.assertEquals(expectedStudent.getPob(), actualStudentDto.getPob());
        Assertions.assertEquals(expectedStudent.getRegNum(), actualStudentDto.getRegNum());
        Assertions.assertEquals(expectedStudent.getGender(), actualStudentDto.getGender());
        Assertions.assertEquals(expectedStudent.getDob(), actualStudentDto.getDob());
    }
}
