package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.SubjectRegistrationMapper;
import com.transkript.reportcard.business.service.StudentApplicationService;
import com.transkript.reportcard.business.service.SubjectRegistrationService;
import com.transkript.reportcard.data.entity.StudentApplication;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import com.transkript.reportcard.data.repository.SubjectRegistrationRepository;
import com.transkript.reportcard.data.repository.SubjectRepository;
import com.transkript.reportcard.exception.EntityException;
import com.transkript.reportcard.exception.ReportCardException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectRegistrationServiceImpl implements SubjectRegistrationService {
    private final SubjectRegistrationRepository subjectRegistrationRepository;
    private final SubjectRegistrationMapper subjectRegistrationMapper;
    private final StudentApplicationService studentApplicationService;
    // TODO change this to the service instead
    private final SubjectRepository subjectRepository;


    @Override
    public EntityResponse addSubjectRegistration(SubjectRegistrationDto subjectRegistrationDto) {
        SubjectRegistration subjectRegistration = subjectRegistrationMapper.mapDtoToSubjectRegistration(subjectRegistrationDto);
        subjectRegistration.setCreatedAt(LocalDateTime.now());
        subjectRegistration.setUpdatedAt(LocalDateTime.now());
        if(subjectRegistrationDto.getApplicationId() == null) {
            throw new ReportCardException.IllegalArgumentException("Application Id is required");
        } else {
            StudentApplication studentApplication = studentApplicationService.getStudentApplicationEntity(subjectRegistrationDto.getApplicationId());
            subjectRegistration.setStudentApplication(studentApplication);
        }
        if(subjectRegistrationDto.getSubjectId() == null) {
            throw new ReportCardException.IllegalArgumentException("Subject Id is required");
        } else {
            Subject subject = subjectRepository.findById(subjectRegistrationDto.getSubjectId())
                    .orElseThrow(() -> new EntityException.EntityNotFoundException("subject", subjectRegistrationDto.getSubjectId()));
            if(subjectRegistrationRepository.findBySubject(subject).isPresent()) {
                throw new ReportCardException.IllegalStateException("Subject already registered: " + subject.getName(), HttpStatus.CONFLICT);
            }
            subjectRegistration.setSubject(subject);
        }
        return EntityResponse.builder()
                .id(subjectRegistrationRepository.save(subjectRegistration).getId())
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
    public void deleteSubjectRegistration(Long subjectRegistrationId) {
        if(subjectRegistrationId == null) {
            throw new ReportCardException.IllegalArgumentException("Subject Registration Id is required");
        }
        SubjectRegistration subjectRegistration = subjectRegistrationRepository.findById(subjectRegistrationId)
                .orElseThrow(() -> new EntityException.EntityNotFoundException("subjectRegistration", subjectRegistrationId));
        subjectRegistrationRepository.deleteById(subjectRegistrationId);
    }
}
