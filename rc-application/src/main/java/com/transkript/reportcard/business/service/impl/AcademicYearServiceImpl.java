package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.AcademicYearDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.AcademicYearMapper;
import com.transkript.reportcard.business.service.AcademicYearService;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.repository.AcademicYearRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class AcademicYearServiceImpl implements AcademicYearService {
    private final AcademicYearRepository academicYearRepository;
    private final AcademicYearMapper academicYearMapper;

    @Override
    public EntityResponse addAcademicYear(AcademicYearDto academicYearDto) {
        AcademicYear academicYear = academicYearMapper.mapDtoToAcademicYear(academicYearDto);
        academicYear.setId(null);
        academicYear = academicYearRepository.save(academicYear);
        return EntityResponse.builder().id(academicYear.getId()).message("Successfully saved entity").entityName("academic year").build();
    }

    @Override
    public List<AcademicYearDto> getAcademicYears() {
        return getAcademicYearEntities().stream().map(academicYearMapper::mapAcademicYearToDto).collect(Collectors.toList());
    }

    @Override
    public AcademicYearDto getAcademicYear(Long id) {
        return academicYearMapper.mapAcademicYearToDto(getAcademicYearEntity(id));
    }

    @Override
    public EntityResponse updateAcademicYear(Long id, AcademicYearDto academicYearDto) {
        if (id != null && academicYearRepository.existsById(id)) {
            AcademicYear academicYear = academicYearMapper.mapDtoToAcademicYear(academicYearDto);
            academicYear.setId(id);
            academicYearRepository.save(academicYear);
            return EntityResponse.builder().id(id).message("successfully deleted entity").entityName("academic year").build();
        }
        throw new EntityException.EntityNotFoundException("academic year", id);
    }

    @Override
    public void deleteAcademicYear(Long id) {
        if (id != null && academicYearRepository.existsById(id)) {
            academicYearRepository.deleteById(id);
        }
        throw new EntityException.EntityNotFoundException("academic year", id);
    }

    @Override
    public AcademicYear getAcademicYearEntity(Long academicYearId) {
        return academicYearRepository.findById(academicYearId)
                .orElseThrow(() -> new EntityException.EntityNotFoundException("academic year", academicYearId));
    }

    @Override
    public List<AcademicYear> getAcademicYearEntities() {
        return academicYearRepository.findAll();
    }
}
