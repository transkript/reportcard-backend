package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.AcademicYearDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.AcademicYear;
import java.util.List;

public interface AcademicYearService {
    
    EntityResponse create( AcademicYearDto var1);

    
    AcademicYearDto getDto( Long id);

    
    EntityResponse update( AcademicYearDto academicYearDto);

    List<AcademicYearDto> getAllDtos();

    void delete(Long var1);

    
    AcademicYear getEntity( Long var1);

    
    List<AcademicYear> getAllEntities();
}
