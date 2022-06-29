package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.TestDefaults;
import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.data.entity.relation.StudentApplication;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import com.transkript.reportcard.data.entity.composite.ApplicationKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        studentApplication.setKey(new ApplicationKey(2L, 3L));
        actualSubjectRegistration.setSubject(Subject.builder().id(1L).build());
    }

    @Test
    void mapDtoToSubjectRegistration() {
        // TODO load from TestDefaults
        SubjectRegistrationDto actualSubjectRegistrationDto = TestDefaults.SUBJECT_REGISTRATION_DTO;
        SubjectRegistration expectedSubjectRegistration = this.subjectRegistrationMapper.mapDtoToSubjectRegistration(actualSubjectRegistrationDto);


    }
}
