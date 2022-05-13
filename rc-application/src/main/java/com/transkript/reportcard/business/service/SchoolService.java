package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.SchoolDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SchoolService {
    String addSchool(SchoolDto schoolDto);

    List<SchoolDto> getSchools();

    SchoolDto getSchool(Long id);

    String updateSchool(Long id, SchoolDto schoolDto);

    String deleteSchool(Long id);
}
