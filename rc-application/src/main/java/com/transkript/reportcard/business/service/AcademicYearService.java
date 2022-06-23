package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.AcademicYearDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.AcademicYear;

import java.util.List;

public interface AcademicYearService {
    EntityResponse addAcademicYear(AcademicYearDto academicYearDto);

    List<AcademicYearDto> getAcademicYears();

    AcademicYearDto getAcademicYear(Long id);

    EntityResponse updateAcademicYear(Long id, AcademicYearDto academicYearDto);

    void deleteAcademicYear(Long id);

    AcademicYear getAcademicYearEntity(Long academicYearId);
    List<AcademicYear> getAcademicYearEntities();
}
