package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.ClassLevelSubDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import java.util.List;

public interface ClassLevelSubService {
    EntityResponse create( ClassLevelSubDto var1);
    List<ClassLevelSubDto> getAllDto();
    List<ClassLevelSubDto> getAllDtoByClassLevel( Long var1);
    ClassLevelSubDto getDto( Long var1);
    EntityResponse update( ClassLevelSubDto classLevelSubDto);
    void delete( Long var1);
    ClassLevelSub getEntity( Long var1);
}
