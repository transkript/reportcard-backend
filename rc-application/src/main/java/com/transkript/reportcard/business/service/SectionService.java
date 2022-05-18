package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.data.entity.Section;

import java.util.List;

public interface SectionService {
    String addSection(SectionDto sectionDto);

    List<SectionDto> getSections();

    SectionDto getSection(Long id);

    Section getSectionById(Long id);

    String updateSection(Long id, SectionDto sectionDto);

    String deleteSection(Long id);
}
