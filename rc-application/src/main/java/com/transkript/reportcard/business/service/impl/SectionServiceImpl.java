package com.transkript.reportcard.business.service.impl;
import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.business.mapper.SectionMapper;
import com.transkript.reportcard.business.service.SchoolService;
import com.transkript.reportcard.business.service.SectionService;
import com.transkript.reportcard.data.entity.School;
import com.transkript.reportcard.data.entity.Section;
import com.transkript.reportcard.data.repository.SectionRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter @Setter
@AllArgsConstructor
@Service
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final SchoolService schoolService;

    @Override
    public String addSection(SectionDto sectionDto) {
        Section section = sectionMapper.mapDtoToSection(sectionDto);
        School school = schoolService.getSchoolEntity(sectionDto.getSchoolId());
        section.setId(null);
        section.setSchool(school);
        sectionRepository.save(section);
        return "Section with name "+ section.getName() + "Successfully Added";

    }
}
