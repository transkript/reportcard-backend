package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.TestDefaults;
import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubjectRegistrationMapperTest {

    private final SubjectRegistrationMapper subjectRegistrationMapper;

    @Autowired
    public SubjectRegistrationMapperTest(SubjectRegistrationMapper subjectRegistrationMapper) {
        this.subjectRegistrationMapper = subjectRegistrationMapper;
    }

    @Test
    void mapSubjectRegistrationToDto() {
        // TODO load from TestDefaults
        SubjectRegistration actualSubjectRegistration = TestDefaults.SUBJECT_REGISTRATION;
        StudentApplication studentApplication = new StudentApplication();
        studentApplication.setId(1L);
        actualSubjectRegistration.setSubject(Subject.builder().id(1L).build());
        actualSubjectRegistration.setStudentApplication(studentApplication);
        SubjectRegistrationDto expectedSubjectRegistrationDto = this.subjectRegistrationMapper.mapSubjectRegistrationToDto(actualSubjectRegistration);

        Assertions.assertEquals(expectedSubjectRegistrationDto.getId(), actualSubjectRegistration.getId());
        Assertions.assertEquals(expectedSubjectRegistrationDto.getCreatedAt(), actualSubjectRegistration.getCreatedAt());
        Assertions.assertEquals(expectedSubjectRegistrationDto.getApplicationId(), actualSubjectRegistration.getStudentApplication().getId());
        Assertions.assertEquals(expectedSubjectRegistrationDto.getSubjectId(), actualSubjectRegistration.getSubject().getId());
    }

    @Test
    void mapDtoToSubjectRegistration() {
        // TODO load from TestDefaults
        SubjectRegistrationDto actualSubjectRegistrationDto = TestDefaults.SUBJECT_REGISTRATION_DTO;
        SubjectRegistration expectedSubjectRegistration = this.subjectRegistrationMapper.mapDtoToSubjectRegistration(actualSubjectRegistrationDto);

        Assertions.assertEquals(expectedSubjectRegistration.getId(), actualSubjectRegistrationDto.getId());
        Assertions.assertEquals(expectedSubjectRegistration.getCreatedAt(), actualSubjectRegistrationDto.getCreatedAt());

    }
}
