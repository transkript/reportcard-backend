package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.SubjectDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.Subject;
import java.util.List;

public interface SubjectService {

    List<SubjectDto> getAllDto();


    SubjectDto getDto( Long var1);


    EntityResponse create( SubjectDto var1);


    EntityResponse update( SubjectDto var2);


    void delete(Long id);


    Subject getEntity( Long id);
}
