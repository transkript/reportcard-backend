package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.business.service.AcademicYearService;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.repository.AcademicYearRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcademicYearServiceImpl implements AcademicYearService {
    private final AcademicYearRepository academicYearRepository;
    @Override
    public AcademicYear getAcademicYearEntity(Long academicYearId) {
        return academicYearRepository.findById(academicYearId)
                .orElseThrow(() -> new EntityException.EntityNotFoundException("academic year", academicYearId));
    }
}
