package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.data.entity.Section;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SectionService {
    Section getSectionEntity(Long id);
    String addSection(SectionDto sectionDto);

    List<SectionDto> getSections();

    SectionDto getSection(Long id);

    String updateSection(Long id, SectionDto sectionDto);

    String deleteSection(Long id);
}
