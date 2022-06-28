package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.api.dto.request.StudentApplicationRequest;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.api.dto.response.StudentApplicationResponse;
import com.transkript.reportcard.data.entity.relation.StudentApplication;
import com.transkript.reportcard.data.entity.composite.ApplicationKey;

import java.util.List;

public interface StudentApplicationService {
    StudentApplication getAsEntity(ApplicationKey applicationKey);

    EntityResponse create(StudentApplicationRequest request);

    List<StudentApplicationDto> getAllAsDto();

    List<StudentApplicationResponse> getAllAsResponseByYear(Long yearId);

    List<StudentApplicationResponse> getAllAsResponses(StudentApplicationRequest request);

    StudentApplicationResponse getAsResponse(StudentApplicationRequest request);

    StudentApplicationDto getAsDto(StudentApplicationDto.ApplicationKeyDto applicationKeyDto);

    EntityResponse delete(StudentApplicationDto.ApplicationKeyDto applicationKeyDto);

}
