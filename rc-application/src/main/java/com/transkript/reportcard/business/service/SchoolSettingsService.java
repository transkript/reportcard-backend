package com.transkript.reportcard.business.service;

import com.transkript.reportcard.api.dto.SchoolSettingsDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.SchoolSettings;

import java.util.List;

public interface SchoolSettingsService {
    EntityResponse save(SchoolSettingsDto schoolSettingsDto);

    EntityResponse update(SchoolSettingsDto schoolSettingsDto);

    SchoolSettingsDto retrieve(Long id);

    SchoolSettingsDto retrieveBySchoolId(Long schoolId);

    List<SchoolSettingsDto> retrieveAll();

    SchoolSettings retrieveEntity(Long id);
}
