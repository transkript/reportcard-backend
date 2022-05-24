package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.entity.composite.ApplicationKey;

import java.util.List;

public interface StudentApplicationService {
    StudentApplication getStudentApplicationEntity(ApplicationKey applicationKey);

    EntityResponse addStudentApplication(StudentApplicationDto applicationDto);

    List<StudentApplicationDto> getStudentApplications();

    StudentApplicationDto getStudentApplication(Long studentId, Long yearId);
}
