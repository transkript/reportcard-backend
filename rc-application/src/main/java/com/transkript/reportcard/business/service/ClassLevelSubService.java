package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.ClassLevelSubDto;
import com.transkript.reportcard.data.entity.ClassLevelSub;

import java.util.List;

public interface ClassLevelSubService {
    String addClassLevelSub(ClassLevelSubDto classLevelSubDto);

    List<ClassLevelSubDto> getClassLevelSubs();

    ClassLevelSubDto getClassLevelSub(Long id);

    String updateClassLevelSub(Long id, ClassLevelSubDto classLevelSubDto);

    String deleteClassLevelSub(Long id);

    ClassLevelSub getClassLevelSubEntity(Long id);
}
