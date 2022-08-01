package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.SchoolDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.School;
import java.util.List;

public interface SchoolService {
    
    School getEntity( Long var1);
    
    EntityResponse create( SchoolDto var1);
    
    List<SchoolDto> getAllDto();
    
    SchoolDto getDto( Long var1);
    
    EntityResponse update( SchoolDto schoolDto);
    
    void delete(Long var1);
}
