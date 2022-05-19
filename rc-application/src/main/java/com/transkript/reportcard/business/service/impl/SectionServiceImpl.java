package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.business.mapper.SectionMapper;
import com.transkript.reportcard.business.service.SchoolService;
import com.transkript.reportcard.business.service.SectionService;
import com.transkript.reportcard.data.entity.School;
import com.transkript.reportcard.data.entity.Section;
import com.transkript.reportcard.data.repository.SectionRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Getter @Setter
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final SchoolService schoolService;

    @Override
    public String addSection(SectionDto sectionDto) {
        School school = schoolService.getSchoolById(sectionDto.getSchoolId());

        Section section = sectionMapper.mapDtoToSection(sectionDto);
        section.setId(null);
        section.setSchool(school);
        sectionRepository.save(section);
        return "Section created successfully";
    }

    @Override
    public List<SectionDto> getSections() {
        return sectionRepository.findAll().stream()
                .map(sectionMapper::mapSectionToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SectionDto getSection(Long id) {
        Optional<Section> sectionOptional = sectionRepository.findById(id);
        return sectionMapper.mapSectionToDto(sectionOptional.orElseThrow(()->{

            throw new EntityException.EntityNotFoundException("Section with id: " + id);
        }));
    }

    @Override
    public Section getSectionById(Long id) {
        return sectionRepository.findById(id).orElseThrow(
                ()->new EntityException.EntityNotFoundException("Section with id: " + id)
        );
    }

    @Override
    public String updateSection(Long id, SectionDto sectionDto) {
        if (id != null && sectionRepository.existsById(id)) {
            School school = schoolService.getSchoolById(sectionDto.getSchoolId());
            Section section = sectionMapper.mapDtoToSection(sectionDto);
            section.setId(id);
            section.setSchool(school);
            sectionRepository.save(section);
            return "Successfully updated section with ID: " + id;
        }
        throw new EntityException.EntityNotFoundException("section");
    }

    @Override
    public String deleteSection(Long id) {
        if (id != null && sectionRepository.existsById(id)) {
            sectionRepository.deleteById(id);
            return "Successfully deleted section with ID: " + id;
        }

        throw new EntityException.EntityNotFoundException("section");
    }
}
