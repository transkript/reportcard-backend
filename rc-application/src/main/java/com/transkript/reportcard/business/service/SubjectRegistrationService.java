package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.relation.SubjectRegistration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubjectRegistrationService {
    EntityResponse addSubjectRegistration(SubjectRegistrationDto subjectRegistrationDto);

    List<EntityResponse> addSubjectRegistrations(Long applicationId, List<SubjectRegistrationDto> subjectRegistrationDtoList);

    List<SubjectRegistrationDto> getSubjectionRegistrations(Long applicationId);

    @Transactional
    void deleteSubjectRegistration(Long registrationId);

    SubjectRegistration getSubjectRegistrationEntity(Long registrationId);

    List<SubjectRegistration> getSubjectRegistrationEntitiesByApplication(Long applicationId);
}
