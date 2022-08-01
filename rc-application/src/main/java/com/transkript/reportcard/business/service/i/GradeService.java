package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.GradeDto;
import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.composite.GradeKey;
import com.transkript.reportcard.data.entity.composite.SubjectRegistrationKey;
import com.transkript.reportcard.data.entity.relation.Grade;

import java.util.List;

public interface GradeService {
    
    EntityResponse create( GradeDto var1);

    
    List<GradeDto> getAllDtoBySequence( Long var1);

    
    List<GradeDto> getAllDtoByRegistration(SubjectRegistrationDto.SubjectRegistrationKeyDto registrationKeyDto);

    
    GradeDto getDto(GradeDto.GradeKeyDto keyDto);

    
    Grade getEntity( GradeKey var1);

    
    List<Grade> getAllEntityBySubjectRegistration(SubjectRegistrationKey registrationKey);

    
    EntityResponse update( GradeDto var1);
}
