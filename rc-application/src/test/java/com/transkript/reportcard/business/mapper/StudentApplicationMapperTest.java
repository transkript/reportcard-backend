package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.ClassLevel;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.entity.Student;
import com.transkript.reportcard.data.entity.relation.StudentApplication;
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
                .key(new ApplicationKey(testStudent.getId(), testAcademicYear.getId()))
                .classLevelSub(testClassLevelSub)
                .build();

        System.out.println(testStudentApplication);
        StudentApplicationDto expectedDto = studentApplicationMapper.mapStudentApplicationToDto(testStudentApplication);

        Assertions.assertEquals(expectedDto.applicationKeyDto().studentId(), testStudentApplication.getKey().getStudentId());
    }

    @Test
    void mapDtoToStudentApplication() {
        testStudentApplicationDto = new StudentApplicationDto(new StudentApplicationDto.ApplicationKeyDto(1L, 1L), List.of());


        StudentApplication expectedSApp = studentApplicationMapper
                .mapDtoToStudentApplication(testStudentApplicationDto);
    }
}
