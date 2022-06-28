package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.SchoolDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.SchoolMapper;
import com.transkript.reportcard.business.service.SchoolService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.config.constants.ResponseSeverity;
import com.transkript.reportcard.data.entity.School;
import com.transkript.reportcard.data.repository.SchoolRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    @Override
    public School getSchoolEntity(Long id) {
        return schoolRepository.findById(id).orElseThrow(() -> new EntityException.EntityNotFoundException("school", id));
    }

    @Override
    public EntityResponse addSchool(SchoolDto schoolDto) {
        School school = schoolMapper.mapDtoToSchool(schoolDto);
        school.setId(null);
        school = schoolRepository.save(school);
        return new EntityResponse(school.getId(), Map.of(),
                "School Successfully created",
                EntityName.SCHOOL, HttpStatus.CREATED.value(), true, ResponseSeverity.SUCCESS
        );
    }

    @Override
    public List<SchoolDto> getSchools() {
        return schoolRepository.findAll().stream()
                .map(schoolMapper::mapSchoolToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SchoolDto getSchool(Long id) {
        return schoolMapper.mapSchoolToDto(getSchoolEntity(id));
    }

    @Override
    public String updateSchool(Long id, SchoolDto schoolDto) {
        if (id != null && schoolRepository.existsById(id)) {
            School school = schoolMapper.mapDtoToSchool(schoolDto);
            school.setId(id);
            schoolRepository.save(school);
            return "school with id: " + id + "successfully updated";
        }
        throw new EntityException.EntityNotFoundException("school", schoolDto.id());
    }

    @Override
    public String deleteSchool(Long id) {
        if (id != null && schoolRepository.existsById(id)) {
            schoolRepository.deleteById(id);
            return "School with ID: " + id + "successfully deleted";
        }
        throw new EntityException.EntityNotFoundException("school");
    }
}
