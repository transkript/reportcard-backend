package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.TermDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.Term;
import java.util.List;

public interface TermService {
    
    EntityResponse create( TermDto var1);

    
    List<TermDto> getAllDto();

    
    TermDto getDto( Long var1);

    
    Term getEntity( Long var1);

    
    EntityResponse update( TermDto termDto);

    
    void delete(Long id);
}
