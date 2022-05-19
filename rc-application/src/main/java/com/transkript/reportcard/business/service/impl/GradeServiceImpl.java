package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.GradeDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.GradeMapper;
import com.transkript.reportcard.business.service.GradeService;
import com.transkript.reportcard.business.service.SequenceService;
import com.transkript.reportcard.business.service.StudentApplicationService;
import com.transkript.reportcard.business.service.SubjectRegistrationService;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.composite.GradeKey;
import com.transkript.reportcard.data.entity.relation.Grade;
import com.transkript.reportcard.data.entity.relation.SubjectRegistration;
import com.transkript.reportcard.data.repository.GradeRepository;
import com.transkript.reportcard.exception.EntityException;
import com.transkript.reportcard.exception.ReportCardException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
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

        SubjectRegistration registration = subjectRegistrationService.getSubjectRegistrationEntity(grade.getGradeKey().getRegistrationId());
        Sequence sequence = sequenceService.getSequenceEntity(gradeDto.getSequenceId());

        grade.setSequence(sequence);
        grade.setSubjectRegistration(registration);
        grade.setGradeKey(GradeKey.builder().sequenceId(sequence.getId()).registrationId(registration.getId()).build());

        grade = gradeRepository.save(grade);

        return EntityResponse.builder()
                .id(registration.getId())
                .ids(Map.of("sequenceId", grade.getGradeKey().getSequenceId(),
                        "registration", grade.getGradeKey().getRegistrationId()))
                .entityName("grade").message("Added grade successfully").date(new Date()).build();
    }

    /**
     * @param applicationId
     * @return
     */
    @Override
    public List<List<GradeDto>> getGradesByApplicationId(Long applicationId) {
        if (applicationId == null) {
            throw new ReportCardException.IllegalArgumentException("Application id is required");
        }
        return subjectRegistrationService.getSubjectRegistrationEntitiesByApplication(applicationId)
                .stream().map(subjectRegistration -> getGradesByRegistrationId(subjectRegistration.getId()))
                .toList();
    }

    /**
     * @param registrationId
     * @return
     */
    @Override
    public List<GradeDto> getGradesByRegistrationId(Long registrationId) {
        SubjectRegistration registration = subjectRegistrationService.getSubjectRegistrationEntity(registrationId);

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
        return gradeRepository.findById(gradeKey).orElseThrow(() -> new EntityException.EntityNotFoundException("grade",
                gradeKey.getRegistrationId(), gradeKey.getSequenceId())
        );
    }
}
