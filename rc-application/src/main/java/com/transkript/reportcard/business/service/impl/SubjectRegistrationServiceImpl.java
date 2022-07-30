package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.SubjectRegistrationMapper;
import com.transkript.reportcard.business.service.i.StudentApplicationService;
import com.transkript.reportcard.business.service.i.StudentApplicationTrialService;
import com.transkript.reportcard.business.service.i.SubjectRegistrationService;
import com.transkript.reportcard.business.service.i.SubjectService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.config.constants.ResponseMessage;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import com.transkript.reportcard.data.entity.relation.StudentApplicationTrial;
import com.transkript.reportcard.data.repository.SubjectRegistrationRepository;
import com.transkript.reportcard.exception.EntityException;
import com.transkript.reportcard.exception.ReportCardException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectRegistrationServiceImpl implements SubjectRegistrationService {
    private final String entityName = EntityName.SUBJECT_REGISTRATION;
    private final SubjectRegistrationRepository subjectRegistrationRepository;
    private final SubjectRegistrationMapper subjectRegistrationMapper;
    private final SubjectService subjectService;
    private final StudentApplicationTrialService studentApplicationTrialService;


    @NotNull
    @Override
    public EntityResponse create(@NotNull SubjectRegistrationDto subjectRegistrationDto) {
        SubjectRegistration subjectRegistration = subjectRegistrationMapper.mapDtoToSubjectRegistration(subjectRegistrationDto);
        subjectRegistration.setCreatedAt(LocalDateTime.now());
        subjectRegistration.setUpdatedAt(LocalDateTime.now());
        subjectRegistration.setId(null);

        StudentApplicationTrial sat = studentApplicationTrialService.getEntity(subjectRegistrationDto.satId());
        Subject subject = subjectService.getSubjectEntity(subjectRegistrationDto.subjectId());

        Optional<SubjectRegistration> srOptional = subjectRegistrationRepository.findByStudentApplicationTrialAndSubject(sat, subject);
        if (srOptional.isPresent()) {
            throw new EntityException.AlreadyExists(entityName, srOptional.get().getId());
        }

        subjectRegistration.setStudentApplicationTrial(sat);
        subjectRegistration.setSubject(subject);

        return new EntityResponse(subject.getId(), ResponseMessage.SUCCESS.created(entityName), true);

    }

    @NotNull
    @Override
    public List<EntityResponse> createMultiple(List<SubjectRegistrationDto> subjectRegistrationDtoList) {
        return subjectRegistrationDtoList.stream().map(this::create).collect(Collectors.toList());
    }

    @NotNull
    @Override
    public List<SubjectRegistrationDto> getDtoList(long satId) {
        // TODO get by a particular student, year, classSub
        return getEntitiesByApplicationTrial(satId)
                .stream().map(subjectRegistrationMapper::mapSubjectRegistrationToDto).collect(Collectors.toList());
    }

    @Override
    public SubjectRegistrationDto getDto(long registrationId) {
        return subjectRegistrationMapper.mapSubjectRegistrationToDto(getEntity(registrationId));
    }

    @Override
    @Transactional
    public void delete(long registrationId) {
        subjectRegistrationRepository.deleteById(registrationId);
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public SubjectRegistration getEntity(long registrationId) {
        return subjectRegistrationRepository.findById(registrationId)
                .orElseThrow(() -> new EntityException.NotFound("subject registration", registrationId));
    }

    @NotNull
    @Override
    public SubjectRegistration getEntity(long satId, long subjectId) {
        StudentApplicationTrial trial = studentApplicationTrialService.getEntity(satId);
        Subject subject = subjectService.getSubjectEntity(subjectId);
        return subjectRegistrationRepository.findByStudentApplicationTrialAndSubject(trial, subject)
                .orElseThrow(() -> new EntityException.NotFound("subject registration", satId, subjectId));
    }

    @NotNull
    @Transactional(readOnly = true)
    public List<SubjectRegistration> getEntitiesByApplicationTrial(long satId) {
        StudentApplicationTrial trial = studentApplicationTrialService.getEntity(satId);
        return subjectRegistrationRepository.findAllByStudentApplicationTrial(trial);
    }
}
