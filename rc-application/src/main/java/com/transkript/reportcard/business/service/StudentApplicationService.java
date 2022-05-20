package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.StudentApplication;

import java.util.List;

public interface StudentApplicationService {
    StudentApplication getStudentApplicationEntity(Long id);

    EntityResponse addStudentApplication(StudentApplicationDto applicationDto);

    List<StudentApplicationDto> getStudentApplications();

    StudentApplicationDto getStudentApplicationById(Long id);
}
