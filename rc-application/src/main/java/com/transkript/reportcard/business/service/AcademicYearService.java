package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.AcademicYearDto;
import com.transkript.reportcard.data.entity.AcademicYear;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import com.transkript.reportcard.data.entity.AcademicYear;

public interface AcademicYearService {
    String addAcademicYear(AcademicYearDto academicYearDto);

    List<AcademicYearDto> getAcademicYears();

    AcademicYearDto getAcademicYear(Long id);

    AcademicYear getAcademicYearById(Long id);

    String updateAcademicYear(Long id, AcademicYearDto academicYearDto);

    String deleteAcademicYear(Long id);
    AcademicYear getAcademicYearEntity(Long academicYearId);
}
