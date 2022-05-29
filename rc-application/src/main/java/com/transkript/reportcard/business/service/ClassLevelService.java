package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.ClassLevelDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.ClassLevel;

import java.util.List;

public interface ClassLevelService {
    EntityResponse addClassLevel(ClassLevelDto classLevelDto);

    List<ClassLevelDto> getClassLevels();

    List<ClassLevelDto> getClassLevelsBySection(Long sectionId);

    ClassLevelDto getClassLevel(Long id);

    ClassLevel getClassLevelEntity(Long id);

    EntityResponse updateClassLevel(Long id, ClassLevelDto classLevelDto);

    void deleteClassLevel(Long id);
}
