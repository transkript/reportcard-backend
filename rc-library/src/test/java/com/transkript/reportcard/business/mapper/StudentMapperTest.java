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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
class StudentMapperTest {
    Student testStudent;
    StudentDto testStudentDto;

    private final StudentMapper studentMapper;

    @Autowired
    public StudentMapperTest(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @BeforeEach
    void setup() {
        testStudent = Student.builder().id(1L).name("test student")
                .pob("test pob").regNum("REGNO")
                .gender("M").dob(LocalDateTime.now())
                .studentApplications(List.of()).build();
        testStudentDto = StudentDto.builder().id(2L).name("test student dto").gender("F")
                .dob(LocalDateTime.now()).pob("test pob dto").regNum("REGNODTO")
                .build();
    }

    @Test
    void mapStudentToDto() {
        StudentDto expectedDto = this.studentMapper.mapStudentToDto(testStudent);

        Assertions.assertEquals(expectedDto.getId(), testStudent.getId());
    }

    @Test
    void mapDtoToStudent() {
    }
}
