package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.GradeDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.composite.GradeKey;
import com.transkript.reportcard.data.entity.relation.Grade;

import java.util.List;

public interface GradeService {
    EntityResponse addGrade(GradeDto gradeDto);

    List<GradeDto> getGradesBySequence(Long sequenceId);

    List<GradeDto> getGradesByRegistration(Long registrationId);

    GradeDto getGrade(Long registrationId, Long sequenceId);

    Grade getGradeEntity(GradeKey gradeKey);
    List<Grade> getGradeEntitiesBySubjectRegistration(Long registrationId);
}
