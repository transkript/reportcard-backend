package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.GradeDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.GradeMapper;
import com.transkript.reportcard.business.service.i.GradeService;
import com.transkript.reportcard.business.service.i.SequenceService;
import com.transkript.reportcard.business.service.i.StudentApplicationService;
import com.transkript.reportcard.business.service.i.SubjectRegistrationService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.config.constants.ResponseMessage;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.SubjectRegistration;
import com.transkript.reportcard.data.entity.composite.GradeKey;
import com.transkript.reportcard.data.entity.relation.Grade;
import com.transkript.reportcard.data.repository.GradeRepository;
import com.transkript.reportcard.exception.EntityException;
import com.transkript.reportcard.exception.ReportCardException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private final SubjectRegistrationService subjectRegistrationService;
    private final SequenceService sequenceService;
    private final StudentApplicationService studentApplicationService;
    private final String entityName = EntityName.GRADE;

    @Override
    public EntityResponse create(GradeDto gradeDto) {
        Grade grade = gradeMapper.gradeDtoToGrade(gradeDto);

        SubjectRegistration registration = subjectRegistrationService.getEntity(gradeDto.registrationId());
        Sequence sequence = sequenceService.getEntity(gradeDto.sequenceId());

        grade.setSequence(sequence);
        grade.setRegistration(registration);
        grade.setKey(GradeKey.builder().sequenceId(sequence.getId()).registrationId(registration.getId()).build());

        grade = gradeRepository.save(grade);

        return new EntityResponse(grade.getKey(), ResponseMessage.SUCCESS.created(entityName), true);
    }

    /**
     * @param sequenceId sequence id
     * @return list of grades
     */
    @Override
    public List<GradeDto> getAllDtoBySequence(Long sequenceId) {
        if (sequenceId == null) {
            throw new ReportCardException.IllegalArgumentException("Sequence id is required");
        }
        Sequence sequence = sequenceService.getEntity(sequenceId);
        return gradeRepository.findAllBySequence(sequence).stream().map(gradeMapper::gradeToGradeDto).toList();
    }

    @Override
    public List<GradeDto> getAllDtoByRegistration(Long registrationId) {
        SubjectRegistration registration = subjectRegistrationService.getEntity(registrationId);

        System.out.println(registration);
        return gradeRepository.findAllByRegistration(registration)
                .stream()
                .map(gradeMapper::gradeToGradeDto).toList();
    }

    @Override
    public GradeDto getDto(GradeDto.GradeKeyDto gradeKeyDto) {
        return gradeMapper.gradeToGradeDto(getEntity(GradeKey.builder()
                .registrationId(gradeKeyDto.registrationId())
                .sequenceId(gradeKeyDto.sequenceId())
                .build()));
    }

    /**
     * @param gradeKey the grade identifier
     * @return the grade entity
     */
    @Override
    public Grade getEntity(GradeKey gradeKey) {
        return gradeRepository.findById(gradeKey).orElseThrow(() -> new EntityException.NotFound("grade", gradeKey));
    }

    @Override
    public List<Grade> getAllEntityBySubjectRegistration(Long registrationId) {
        SubjectRegistration subjectRegistration = subjectRegistrationService.getEntity(registrationId);
        return gradeRepository.findAllByRegistration(subjectRegistration);
    }

    @Override
    public EntityResponse update(GradeDto gradeDto) {
        GradeKey gradeKey = GradeKey.builder().registrationId(
                gradeDto.registrationId()
        ).sequenceId(gradeDto.sequenceId()).build();
        if (!gradeRepository.existsById(gradeKey)) {
            throw new EntityException.NotFound("grade", gradeKey);
        }
        Grade grade = getEntity(gradeKey);
        grade.setScore(gradeDto.score());
        gradeRepository.save(grade);
        return new EntityResponse(gradeKey, ResponseMessage.SUCCESS.created(entityName), true);
    }
}
