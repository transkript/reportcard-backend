package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.SequenceDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.Sequence;
import java.util.List;

public interface SequenceService {
    
    EntityResponse create( SequenceDto var1);
    
    List<SequenceDto> getAllDto();
    
    SequenceDto getDto( Long var1);
    
    EntityResponse update( SequenceDto sequenceDto);
    
    void delete(Long id);
    
    Sequence getEntity( Long id);
}
