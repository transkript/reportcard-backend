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
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private final SubjectRegistrationService subjectRegistrationService;
    private final SequenceService sequenceService;
    private final StudentApplicationService studentApplicationService;
    private final String entityName = EntityName.GRADE;

    /**
     * @param gradeDto
     * @return
     */
    @Override
    public EntityResponse addGrade(GradeDto gradeDto) {
        if (gradeDto.getRegistrationId() == null) {
            throw new ReportCardException.IllegalArgumentException("Student registration id is required");
        }
        if (gradeDto.getSequenceId() == null) {
            throw new ReportCardException.IllegalArgumentException("Sequence id is required");
        }

        Grade grade = gradeMapper.mapDtoToGrade(gradeDto);

        SubjectRegistration registration = subjectRegistrationService.getEntity(grade.getGradeKey().getRegistrationId());
        Sequence sequence = sequenceService.getEntity(gradeDto.getSequenceId());

        grade.setSequence(sequence);
        grade.setSubjectRegistration(registration);
        grade.setGradeKey(GradeKey.builder().sequenceId(sequence.getId()).registrationId(registration.getId()).build());

        grade = gradeRepository.save(grade);

        return new EntityResponse(
                Map.of("sequenceId", grade.getGradeKey().getSequenceId(), "registration", grade.getGradeKey().getRegistrationId()),
                ResponseMessage.SUCCESS.created(entityName), true);
    }

    /**
     * @param sequenceId sequence id
     * @return list of grades
     */
    @Override
    public List<GradeDto> getGradesBySequence(Long sequenceId) {
        if (sequenceId == null) {
            throw new ReportCardException.IllegalArgumentException("Sequence id is required");
        }
        Sequence sequence = sequenceService.getEntity(sequenceId);
        return gradeRepository.findAllBySequence(sequence).stream().map(gradeMapper::mapGradeToDto).toList();
    }

    /**
     * @param registrationId registration id
     * @return list of grades
     */
    @Override
    public List<GradeDto> getGradesByRegistration(Long registrationId) {
        SubjectRegistration registration = subjectRegistrationService.getEntity(registrationId);

        System.out.println(registration);
        return gradeRepository.findAllBySubjectRegistration(registration)
                .stream()
                .map(gradeMapper::mapGradeToDto).toList();
    }

    /**
     * @param registrationId
     * @param sequenceId
     * @return
     */
    @Override
    public GradeDto getGrade(Long registrationId, Long sequenceId) {
        return gradeMapper.mapGradeToDto(getGradeEntity(GradeKey.builder()
                .registrationId(registrationId)
                .sequenceId(sequenceId)
                .build()));
    }

    /**
     * @param gradeKey the grade identifier
     * @return the grade entity
     */
    @Override
    public Grade getGradeEntity(GradeKey gradeKey) {
        return gradeRepository.findById(gradeKey).orElseThrow(() -> new EntityException.NotFound("grade",
                gradeKey.getRegistrationId(), gradeKey.getSequenceId())
        );
    }

    @Override
    public List<Grade> getGradeEntitiesBySubjectRegistration(Long registrationId) {
        SubjectRegistration subjectRegistration = subjectRegistrationService.getEntity(registrationId);
        return gradeRepository.findAllBySubjectRegistration(subjectRegistration);
    }

    @Override
    public EntityResponse updateGrade(GradeDto gradeDto) {
        GradeKey gradeKey = GradeKey.builder().registrationId(gradeDto.getRegistrationId()).sequenceId(gradeDto.getSequenceId()).build();
        if (!gradeRepository.existsById(gradeKey)) {
            throw new EntityException.NotFound("grade", gradeKey.getRegistrationId(), gradeKey.getSequenceId());
        }
        Grade grade = getGradeEntity(gradeKey);
        grade.setScore(gradeDto.getScore());
        gradeRepository.save(grade);
        return new EntityResponse(
                Map.of("sequenceId", grade.getGradeKey().getSequenceId(), "registration", grade.getGradeKey().getRegistrationId()),
                ResponseMessage.SUCCESS.created(entityName), true);
    }
}
