package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.ClassLevel;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.entity.composite.ApplicationKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class StudentApplicationMapperTest {
    private final StudentApplicationMapper studentApplicationMapper;
    StudentApplication testStudentApplication;
    StudentApplicationDto testStudentApplicationDto;
    Student testStudent;
    AcademicYear testAcademicYear;
    ClassLevel testClassLevel = ClassLevel.builder().id(1L).name("class level").build();
    ClassLevelSub testClassLevelSub = ClassLevelSub.builder().id(1L).name("class level sub").studentApplications(List.of()).classLevel(testClassLevel).build();

    @Autowired
    StudentApplicationMapperTest(StudentApplicationMapper studentApplicationMapper) {
        this.studentApplicationMapper = studentApplicationMapper;
    }

    @Test
    void mapStudentApplicationToDto() {
        testStudent = Student.builder().id(2L).build();
        testAcademicYear = new AcademicYear();
        testAcademicYear.setId(4L);
        testStudentApplication = StudentApplication.builder()
                .applicationKey(new ApplicationKey(testStudent.getId(), testAcademicYear.getId()))
                .createdAt(LocalDateTime.now())
                .student(testStudent)
                .academicYear(testAcademicYear)
                .subjectRegistrations(List.of())
                .classLevelSub(testClassLevelSub)
                .build();

        System.out.println(testStudentApplication);
        StudentApplicationDto expectedDto = studentApplicationMapper.mapStudentApplicationToDto(testStudentApplication);

        Assertions.assertEquals(expectedDto.getStudentId(), testStudentApplication.getApplicationKey().getStudentId());
        Assertions.assertEquals(expectedDto.getAcademicYearId(), testStudentApplication.getApplicationKey().getYearId());
        Assertions.assertEquals(expectedDto.getCreatedAt(), testStudentApplication.getCreatedAt());
        Assertions.assertEquals(expectedDto.getStudentId(), testStudentApplication.getStudent().getId());
        Assertions.assertEquals(expectedDto.getAcademicYearId(), testStudentApplication.getAcademicYear().getId());
        Assertions.assertEquals(expectedDto.getNumberOfSubjects(), testStudentApplication.getSubjectRegistrations().size());
    }

    @Test
    void mapDtoToStudentApplication() {
        testStudentApplicationDto = StudentApplicationDto.builder()
                .createdAt(LocalDateTime.now())
                .build();

        StudentApplication expectedSApp = studentApplicationMapper
                .mapDtoToStudentApplication(testStudentApplicationDto);

        Assertions.assertEquals(expectedSApp.getCreatedAt(), testStudentApplicationDto.getCreatedAt());
    }
}
