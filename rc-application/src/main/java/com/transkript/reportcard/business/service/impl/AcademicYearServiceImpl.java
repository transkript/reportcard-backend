package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.AcademicYearDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.AcademicYearMapper;
import com.transkript.reportcard.business.service.i.AcademicYearService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.config.constants.ResponseMessage;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.repository.AcademicYearRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class AcademicYearServiceImpl implements AcademicYearService {
    private final AcademicYearRepository academicYearRepository;
    private final AcademicYearMapper academicYearMapper;
    private final String entityName = EntityName.ACADEMIC_YEAR;

    @Override
    public EntityResponse create(AcademicYearDto academicYearDto) {
        AcademicYear academicYear = academicYearMapper.mapDtoToAcademicYear(academicYearDto);
        academicYear.setId(null);
        academicYear = academicYearRepository.save(academicYear);
        return new EntityResponse(academicYear.getId(), ResponseMessage.SUCCESS.created(entityName), true);
    }

    @Override
    public List<AcademicYearDto> getAcademicYears() {
        return getAcademicYearEntities().stream().map(academicYearMapper::mapAcademicYearToDto).collect(Collectors.toList());
    }

    @Override
    public AcademicYearDto getDto(Long id) {
        return academicYearMapper.mapAcademicYearToDto(getEntity(id));
    }

    @Override
    public EntityResponse update(Long id, AcademicYearDto academicYearDto) {
        if (id != null && academicYearRepository.existsById(id)) {
            AcademicYear academicYear = academicYearMapper.mapDtoToAcademicYear(academicYearDto);
            academicYear.setId(id);
            academicYearRepository.save(academicYear);
            return new EntityResponse(academicYear.getId(), ResponseMessage.SUCCESS.created(entityName), true);
        }
        throw new EntityException.NotFound("academic year", id);
    }

    @Override
    public void delete(Long id) {
        if (id != null && academicYearRepository.existsById(id)) {
            academicYearRepository.deleteById(id);
        }
        throw new EntityException.NotFound("academic year", id);
    }

    @Override
    public AcademicYear getEntity(Long academicYearId) {
        return academicYearRepository.findById(academicYearId)
                .orElseThrow(() -> new EntityException.NotFound("academic year", academicYearId));
    }

    @Override
    public List<AcademicYear> getAcademicYearEntities() {
        return academicYearRepository.findAll();
    }
}
