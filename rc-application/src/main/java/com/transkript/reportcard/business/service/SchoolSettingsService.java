package com.transkript.reportcard.business.service;

import com.transkript.reportcard.api.dto.SchoolSettingsDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;

public interface SchoolSettingsService {
    EntityResponse saveSettings(SchoolSettingsDto schoolSettingsDto);

    SchoolSettingsDto getSettings();
}
