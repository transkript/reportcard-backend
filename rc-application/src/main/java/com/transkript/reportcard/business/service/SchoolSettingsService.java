package com.transkript.reportcard.business.service;

import com.transkript.reportcard.api.dto.SchoolSettingsDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import org.springframework.http.ResponseEntity;

public interface SchoolSettingsService {
    EntityResponse addSettings(SchoolSettingsDto schoolSettingsDto);
}
