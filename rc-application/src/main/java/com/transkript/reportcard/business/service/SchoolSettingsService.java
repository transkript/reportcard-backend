package com.transkript.reportcard.business.service;

import com.transkript.reportcard.api.dto.SchoolSettingsDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.SchoolSettings;

public interface SchoolSettingsService {
    EntityResponse saveSettings(SchoolSettingsDto schoolSettingsDto);

    SchoolSettingsDto getSettings();

    SchoolSettings getSettingsEntity();
}
