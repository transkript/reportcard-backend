package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.SubjectRegistrationMapper;
import com.transkript.reportcard.business.service.StudentApplicationService;
import com.transkript.reportcard.business.service.StudentApplicationTrialService;
import com.transkript.reportcard.business.service.SubjectRegistrationService;
import com.transkript.reportcard.business.service.SubjectService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import com.transkript.reportcard.data.entity.relation.StudentApplicationTrial;
import com.transkript.reportcard.data.repository.SubjectRegistrationRepository;
import com.transkript.reportcard.exception.EntityException;
import com.transkript.reportcard.exception.ReportCardException;
import lombok.RequiredArgsConstructor;
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
    private final SubjectRegistrationRepository subjectRegistrationRepository;
    private final SubjectRegistrationMapper subjectRegistrationMapper;
    private final StudentApplicationService studentApplicationService;
    private final SubjectService subjectService;
    private final StudentApplicationTrialService studentApplicationTrialService;


    @Override
    public EntityResponse addSubjectRegistration(SubjectRegistrationDto subjectRegistrationDto) {
        SubjectRegistration subjectRegistration = subjectRegistrationMapper.mapDtoToSubjectRegistration(subjectRegistrationDto);
        subjectRegistration.setCreatedAt(LocalDateTime.now()); subjectRegistration.setUpdatedAt(LocalDateTime.now());
        subjectRegistration.setId(null);

        StudentApplicationTrial sat = studentApplicationTrialService.getEntity(subjectRegistrationDto.satId());
        Subject subject = subjectService.getSubjectEntity(subjectRegistrationDto.subjectId());

        Optional<SubjectRegistration> srOptional = subjectRegistrationRepository.findByStudentApplicationTrialAndSubject(sat, subject);
        if (srOptional.isPresent()) {
            return EntityResponse.builder().id(srOptional.get().getId()).entityName(EntityName.SUBJECT_REGISTRATION)
                    .message("Subject Registration already exists").build();
        }

        subjectRegistration.setStudentApplicationTrial(sat);
        subjectRegistration.setSubject(subject);

        return EntityResponse.builder().id(subjectRegistrationRepository.save(subjectRegistration).getId())
                .entityName("subjectRegistration").message("Subject Registration added successfully").build();
    }

    @Override
    public List<EntityResponse> addSubjectRegistrations(List<SubjectRegistrationDto> subjectRegistrationDtoList) {
        return subjectRegistrationDtoList.stream().map(this::addSubjectRegistration).collect(Collectors.toList());
    }

    @Override
    public List<SubjectRegistrationDto> getSubjectionRegistrations(Long satId) {
        // TODO get by a particular student, year, classSub
        return getSubjectRegistrationEntitiesByApplicationTrial(satId)
                .stream().map(subjectRegistrationMapper::mapSubjectRegistrationToDto).collect(Collectors.toList());
    }

    @Override
    public SubjectRegistrationDto getSubjectRegistration(Long registrationId) {
        return subjectRegistrationMapper.mapSubjectRegistrationToDto(getSubjectRegistrationEntity(registrationId));
    }

    @Override
    @Transactional
    public void deleteSubjectRegistration(Long registrationId) {
        if (registrationId == null) {
            throw new ReportCardException.IllegalArgumentException("Subject registration Id is required");
        }
        subjectRegistrationRepository.findById(registrationId)
                .ifPresentOrElse(subjectRegistrationRepository::delete, () -> {
                    throw new EntityException.EntityNotFoundException("subjectRegistration", registrationId);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public SubjectRegistration getSubjectRegistrationEntity(Long registrationId) {
        return subjectRegistrationRepository.findById(registrationId)
                .orElseThrow(() -> new EntityException.EntityNotFoundException("subject registration", registrationId));
    }

    @Override
    public SubjectRegistration getSubjectRegistrationEntity(Long satId, Long subjectId) {
        StudentApplicationTrial trial = studentApplicationTrialService.getEntity(satId);
        Subject subject = subjectService.getSubjectEntity(subjectId);
        return subjectRegistrationRepository.findByStudentApplicationTrialAndSubject(trial, subject)
                .orElseThrow(() -> new EntityException.EntityNotFoundException("subject registration", satId, subjectId));
    }

    @Transactional(readOnly = true)
    public List<SubjectRegistration> getSubjectRegistrationEntitiesByApplicationTrial(Long satId) {
        Objects.requireNonNull(satId, "Student Application Trial Id is required");

        StudentApplicationTrial trial = studentApplicationTrialService.getEntity(satId);
        return subjectRegistrationRepository.findAllByStudentApplicationTrial(trial);
    }
}
