package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.SubjectRegistrationMapper;
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
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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
        StudentApplicationTrial sat = studentApplicationTrialService.getEntity(subjectRegistrationDto.satId());
        Subject subject = subjectService.getEntity(subjectRegistrationDto.subjectId());

        Optional<SubjectRegistration> srOptional = subjectRegistrationRepository
                .findBySubjectAndStudentApplicationTrial(subject, sat);
        if (srOptional.isPresent()) {
            throw new EntityException.AlreadyExists(entityName, srOptional.get().getId());
        }
        SubjectRegistration subjectRegistration = subjectRegistrationRepository.save(
                SubjectRegistration.builder()
                        .studentApplicationTrial(sat).subject(subject).id(null)
                        .createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build()
        );

        return new EntityResponse(subjectRegistration.getId(), ResponseMessage.SUCCESS.created(entityName), true);
    }

    @NotNull
    @Override
    public List<EntityResponse> createMultiple(List<SubjectRegistrationDto> subjectRegistrationDtoList) {
        return subjectRegistrationDtoList.stream().map(this::create).collect(Collectors.toList());
    }

    @NotNull
    @Override
    public List<SubjectRegistrationDto> getAllDtoBySAT(Long satId) {
        // TODO get by a particular student, year, classSub
        StudentApplicationTrial sat = studentApplicationTrialService.getEntity(satId);
        return subjectRegistrationRepository.findAllByStudentApplicationTrial(sat)
                .stream().map(subjectRegistrationMapper::subjectRegistrationToSubjectRegistrationDto).toList();
    }

    @Override
    public SubjectRegistrationDto getDto(Long id) {
        return subjectRegistrationMapper.subjectRegistrationToSubjectRegistrationDto(getEntity(id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        subjectRegistrationRepository.deleteById(id);
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public SubjectRegistration getEntity(Long id) {
        return subjectRegistrationRepository.findById(id)
                .orElseThrow(() -> new EntityException.NotFound("subject registration", id));
    }

    @NotNull
    @Override
    public SubjectRegistration getEntityBySubjectAndSat(Long subjectId, Long satId) {
        Subject subject = subjectService.getEntity(subjectId);
        StudentApplicationTrial sat = studentApplicationTrialService.getEntity(satId);

        return subjectRegistrationRepository.findBySubjectAndStudentApplicationTrial(subject, sat)
                .orElseThrow(() -> new EntityException.NotFound("subject registration", List.of(subjectId, satId)));
    }
}
