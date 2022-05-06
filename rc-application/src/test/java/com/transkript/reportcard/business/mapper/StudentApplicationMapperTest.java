package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.entity.StudentApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class StudentApplicationMapperTest {
    StudentApplication testStudentApplication;
    StudentApplicationDto testStudentApplicationDto;
    Student testStudent;
    AcademicYear testAcademicYear;

    private final StudentApplicationMapper studentApplicationMapper;

    @Autowired
    StudentApplicationMapperTest(StudentApplicationMapper studentApplicationMapper) {
        this.studentApplicationMapper = studentApplicationMapper;
    }

    @BeforeEach
    void setUp(){
        testStudent = Student.builder().name("name").build();
        testAcademicYear = new AcademicYear();
        testAcademicYear.setName("2020");
        testStudentApplication = StudentApplication.builder()
                .id(1L)
                .createdAt(LocalDateTime.now())
                .student(testStudent)
                .academicYear(testAcademicYear)
                .subjectRegistrations(List.of())
                .build();

        testStudentApplicationDto = StudentApplicationDto.builder()
                .id(2L)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    void mapStudentApplicationToDto() {
        StudentApplicationDto expectedDto = studentApplicationMapper
                .mapStudentApplicationToDto(testStudentApplication);

        Assertions.assertEquals(expectedDto.getId(), testStudentApplication.getId());
    }

    @Test
    void mapDtoToStudentApplication() {
        StudentApplication expectedSApp = studentApplicationMapper
                .mapDtoToStudentApplication(testStudentApplicationDto);

        Assertions.assertEquals(expectedSApp.getId(), testStudentApplicationDto.getId());
    }
}