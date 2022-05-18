package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.AcademicYearDto;
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
    public String addAcademicYear(AcademicYearDto academicYearDto) {
        AcademicYear academicYear = academicYearMapper.mapDtoToAcademicYear(academicYearDto);
        academicYear.setId(null);
        academicYearRepository.save(academicYear);
        return "Added Academic year successfully";
    }

    @Override
    public List<AcademicYearDto> getAcademicYears() {
        return academicYearRepository.findAll().stream()
                .map(academicYearMapper::mapAcademicYearToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AcademicYearDto getAcademicYear(Long id) {
        return academicYearMapper.mapAcademicYearToDto(academicYearRepository.findById(id).orElseThrow(
            ()->{
                throw new EntityException.EntityNotFoundException("Academic year with id " + id);
            }
        ));
    }

    @Override
    public AcademicYear getAcademicYearById(Long id) {
        return academicYearRepository.findById(id).orElseThrow(
                ()->{
                    throw new EntityException.EntityNotFoundException("Academic year with id " + id);
                }
        );
    }

    @Override
    public String updateAcademicYear(Long id, AcademicYearDto academicYearDto) {
        if (id != null && academicYearRepository.existsById(id)) {
            AcademicYear academicYear = academicYearMapper.mapDtoToAcademicYear(academicYearDto);
            academicYear.setId(id);
            academicYearRepository.save(academicYear);
            return "Successfully updated Academic year with ID: " + id;
        }
        throw new EntityException.EntityNotFoundException("Academic year with id: " + id);
    }

    @Override
    public String deleteAcademicYear(Long id) {
        if (id != null && academicYearRepository.existsById(id)) {
            academicYearRepository.deleteById(id);
            return "Successfully deleted academic  year with ID: " + id;
        }
        throw new EntityException.EntityNotFoundException("Academic year with id: " + id);
    }

    @Override
    public AcademicYear getAcademicYearEntity(Long academicYearId) {
        return academicYearRepository.findById(academicYearId)
                .orElseThrow(() -> new EntityException.EntityNotFoundException("academic year", academicYearId));
    }
}
