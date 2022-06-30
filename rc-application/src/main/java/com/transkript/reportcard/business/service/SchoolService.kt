package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.SchoolDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.School;

import java.util.List;

public interface SchoolService {

    School getSchoolEntity(Long id);

    EntityResponse addSchool(SchoolDto schoolDto);

    List<SchoolDto> getSchools();

    SchoolDto getSchool(Long id);

    String updateSchool(Long id, SchoolDto schoolDto);

    String deleteSchool(Long id);
}
