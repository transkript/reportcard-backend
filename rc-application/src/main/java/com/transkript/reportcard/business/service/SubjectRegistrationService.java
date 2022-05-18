package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;

import java.util.List;

public interface SubjectRegistrationService {
    EntityResponse addSubjectRegistration(SubjectRegistrationDto subjectRegistrationDto);

    List<EntityResponse> addSubjectRegistrations(Long applicationId, List<SubjectRegistrationDto> subjectRegistrationDtoList);

    List<SubjectRegistrationDto> getSubjectionRegistrations(Long applicationId);

    void deleteSubjectRegistration(Long subjectRegistrationId);
}
