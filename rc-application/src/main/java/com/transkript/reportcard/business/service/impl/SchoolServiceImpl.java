package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.SchoolDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.SchoolMapper;
import com.transkript.reportcard.business.service.i.AcademicYearService;
import com.transkript.reportcard.business.service.i.SchoolService;
import com.transkript.reportcard.business.service.i.SequenceService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.config.constants.ResponseMessage;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.School;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.repository.SchoolRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Service
public class SchoolServiceImpl implements SchoolService {
    private final String entityName = EntityName.SCHOOL;
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;
    private AcademicYearService academicYearService;
    private SequenceService sequenceService;

    @Override
    public School getEntity(Long id) {
        return schoolRepository.findById(id).orElseThrow(() -> new EntityException.NotFound("school", id));
    }

    @Override
    public EntityResponse create(SchoolDto schoolDto) {
        School school = schoolMapper.mapDtoToSchool(schoolDto);
        school.setId(null);
        school.setCurrentYear(null);
        school.setCurrentSequence(null);
        school = schoolRepository.save(school);
        return new EntityResponse(school.getId(), ResponseMessage.SUCCESS.created(entityName), true);
    }

    @Override
    public List<SchoolDto> getAllDto() {
        return schoolRepository.findAll().stream()
                .map(schoolMapper::mapSchoolToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SchoolDto getDto(Long id) {
        return schoolMapper.mapSchoolToDto(getEntity(id));
    }

    @Override
    public EntityResponse update(SchoolDto schoolDto) {
        School school = getEntity(schoolDto.id());
        AcademicYear year = academicYearService.getEntity(schoolDto.currYearId());
        Sequence sequence = sequenceService.getEntity(schoolDto.currSeqId());
        school.setCurrentYear(year);
        school.setCurrentSequence(sequence);
        school.setCurrentTerm(sequence.getTerm().getName());
        school = schoolRepository.save(school);
        return new EntityResponse(school.getId(), ResponseMessage.SUCCESS.updated(entityName), true);
    }

    @Override
    public void delete(Long id) {
        schoolRepository.deleteById(id);
    }
}
