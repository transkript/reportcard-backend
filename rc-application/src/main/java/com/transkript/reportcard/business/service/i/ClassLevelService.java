package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.ClassLevelDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.ClassLevel;
import java.util.List;

public interface ClassLevelService {
    
    EntityResponse create( ClassLevelDto var1);

    
    List<ClassLevelDto> getAllDtos();

    
    List<ClassLevelDto> getAllDtosBySection( Long var1);

    
    ClassLevelDto getDto( Long var1);

    
    ClassLevel getEntity( Long var1);

    
    EntityResponse update( ClassLevelDto classLevelDto);

    void delete( Long var1);
}
