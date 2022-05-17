package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.ClassLevelDto;
import com.transkript.reportcard.data.entity.ClassLevel;

import java.util.List;

public interface ClassLevelService {
    String addClassLevel(ClassLevelDto classLevelDto);

    List<ClassLevelDto> getClassLevels();

    ClassLevelDto getClassLevel(Long id);

    String updateClassLevel(Long id, ClassLevelDto classLevelDto);

    String deleteClassLevel(Long id);
}
