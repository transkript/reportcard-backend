package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.SubjectRegistrationMapper;
import com.transkript.reportcard.business.service.StudentApplicationService;
import com.transkript.reportcard.business.service.SubjectRegistrationService;
import com.transkript.reportcard.business.service.SubjectService;
import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.composite.SubjectRegistrationKey;
import com.transkript.reportcard.data.entity.relation.SubjectRegistration;
import com.transkript.reportcard.data.repository.SubjectRegistrationRepository;
import com.transkript.reportcard.exception.EntityException;
import com.transkript.reportcard.exception.ReportCardException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectRegistrationServiceImpl implements SubjectRegistrationService {
    private final SubjectRegistrationRepository subjectRegistrationRepository;
    private final SubjectRegistrationMapper subjectRegistrationMapper;
    private final StudentApplicationService studentApplicationService;
    private final SubjectService subjectService;


    @Override
    public EntityResponse addSubjectRegistration(SubjectRegistrationDto subjectRegistrationDto) {
        SubjectRegistration subjectRegistration = subjectRegistrationMapper.mapDtoToSubjectRegistration(subjectRegistrationDto);
        subjectRegistration.setCreatedAt(LocalDateTime.now());
        subjectRegistration.setUpdatedAt(LocalDateTime.now());
        subjectRegistration.setSubjectRegistrationKey(new SubjectRegistrationKey());
        if(subjectRegistrationDto.getApplicationId() == null) {
            throw new ReportCardException.IllegalArgumentException("Application Id is required");
        } else {
            StudentApplication studentApplication = studentApplicationService.getStudentApplicationEntity(subjectRegistrationDto.getApplicationId());
            subjectRegistration.setStudentApplication(studentApplication);
            subjectRegistration.getSubjectRegistrationKey().setStudentApplicationId(studentApplication.getId());
        }
        if(subjectRegistrationDto.getSubjectId() == null) {
            throw new ReportCardException.IllegalArgumentException("Subject Id is required");
        } else {
            Subject subject = subjectService.getSubjectEntity(subjectRegistrationDto.getSubjectId());
            if(subjectRegistrationRepository.findBySubject(subject).isPresent()) {
                throw new ReportCardException.IllegalStateException("Subject already registered: " + subject.getName(), HttpStatus.CONFLICT);
            }
            subjectRegistration.setSubject(subject);
            subjectRegistration.getSubjectRegistrationKey().setSubjectId(subject.getId());
        }
        return EntityResponse.builder()
                .id(subjectRegistrationRepository.save(subjectRegistration).getSubjectRegistrationKey().getStudentApplicationId())
                .entityName("subjectRegistration")
                .message("Subject Registration added successfully")
                .build();
    }

    @Override
    public List<EntityResponse> addSubjectRegistrations(Long applicationId, List<SubjectRegistrationDto> subjectRegistrationDtoList) {
        if(applicationId == null ) {
            throw new ReportCardException.IllegalArgumentException("Application Id is required");
        }
        return subjectRegistrationDtoList.stream()
                .peek(subjectRegistrationDto -> subjectRegistrationDto.setApplicationId(applicationId))
                .map(this::addSubjectRegistration)
                .collect(Collectors.toList());
    }

    @Override
    public List<SubjectRegistrationDto> getSubjectionRegistrations(Long applicationId) {
        if(applicationId == null) {
            throw new ReportCardException.IllegalArgumentException("Application Id is required");
        }
        StudentApplication application = studentApplicationService.getStudentApplicationEntity(applicationId);
        return subjectRegistrationRepository.findAllByStudentApplication(application)
                .stream().map(subjectRegistrationMapper::mapSubjectRegistrationToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteSubjectRegistration(Long subjectId, Long applicationId) {
        if(subjectId == null) {
            throw new ReportCardException.IllegalArgumentException("Subject Registration Id is required");
        }
        if(applicationId == null) {
            throw new ReportCardException.IllegalArgumentException("Application Id is required");
        }
        SubjectRegistrationKey subjectRegistrationKey = SubjectRegistrationKey.builder()
                .subjectId(subjectId).studentApplicationId(applicationId).build();
        subjectRegistrationRepository.findById(subjectRegistrationKey)
                .ifPresentOrElse(subjectRegistrationRepository::delete, () -> {
                    throw new EntityException.EntityNotFoundException("subjectRegistration", subjectId, applicationId);
                });
    }
}
