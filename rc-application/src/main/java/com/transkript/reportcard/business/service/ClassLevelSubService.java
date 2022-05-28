package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.ClassLevelSubDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.ClassLevelSub;

import java.util.List;

public interface ClassLevelSubService {
    EntityResponse addClassLevelSub(ClassLevelSubDto classLevelSubDto);

    List<ClassLevelSubDto> getClassLevelSubs();

    List<ClassLevelSubDto> getClassLevelSubsByClassLevel(Long levelId);

    ClassLevelSubDto getClassLevelSub(Long id);

    EntityResponse updateClassLevelSub(Long id, ClassLevelSubDto classLevelSubDto);

    String deleteClassLevelSub(Long id);

    ClassLevelSub getClassLevelSubEntity(Long id);

}
