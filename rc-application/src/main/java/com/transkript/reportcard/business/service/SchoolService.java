package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.SchoolDto;
import com.transkript.reportcard.data.entity.School;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SchoolService {

    School getSchoolEntity(Long id);

    String addSchool(SchoolDto schoolDto);

    List<SchoolDto> getSchools();

    SchoolDto getSchool(Long id);

    School getSchoolById(Long id);

    String updateSchool(Long id, SchoolDto schoolDto);

    String deleteSchool(Long id);
}
