package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubjectRegistrationService {
    EntityResponse addSubjectRegistration(SubjectRegistrationDto subjectRegistrationDto);

    List<EntityResponse> addSubjectRegistrations(List<SubjectRegistrationDto> subjectRegistrationDtoList);

    List<SubjectRegistrationDto> getSubjectionRegistrations(Long satId);

    SubjectRegistrationDto getSubjectRegistration(Long registrationId);

    @Transactional
    void deleteSubjectRegistration(Long registrationId);

    SubjectRegistration getSubjectRegistrationEntity(Long registrationId);

    SubjectRegistration getSubjectRegistrationEntity(Long satId, Long subjectId);

    List<SubjectRegistration> getSubjectRegistrationEntitiesByApplicationTrial(Long satId);
}
