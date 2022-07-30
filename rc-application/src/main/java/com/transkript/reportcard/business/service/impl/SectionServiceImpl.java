package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.business.mapper.SectionMapper;
import com.transkript.reportcard.business.service.i.SchoolService;
import com.transkript.reportcard.business.service.i.SectionService;
import com.transkript.reportcard.data.entity.School;
import com.transkript.reportcard.data.entity.Section;
import com.transkript.reportcard.data.repository.SectionRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Service
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final SchoolService schoolService;

    @Override
    public Section getSectionEntity(Long id) {
        return sectionRepository.findById(id).orElseThrow(() -> {
            throw new EntityException.NotFound("section");
        });

    }

    @Override
    public String addSection(SectionDto sectionDto) {
        Section section = sectionMapper.mapDtoToSection(sectionDto);
        School school = schoolService.getEntity(sectionDto.getSchoolId());
        section.setId(null);
        section.setSchool(school);
        sectionRepository.save(section);
        return "Section with name " + section.getName() + "Successfully Added";

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
        return sectionMapper.mapSectionToDto(sectionOptional.orElseThrow(() -> {
            throw new EntityException.NotFound("section");
        }));
    }

    @Override
    public String updateSection(Long id, SectionDto sectionDto) {
        if (id != null && sectionRepository.existsById(id) && id.equals(sectionDto.getId())) {
            Section section = sectionRepository.getById(id);
            if (sectionDto.getName() != null && !Objects.equals(sectionDto.getName(), section.getName())) {
                section.setName(sectionDto.getName());
            }
            if (sectionDto.getCategory() != null & !Objects.equals(sectionDto.getCategory(), section.getCategory())) {
                section.setCategory(sectionDto.getCategory());
            }

            if (sectionDto.getSchoolId() != null && !Objects.equals(sectionDto.getSchoolId(), section.getSchool().getId())) {
                School school = schoolService.getEntity(sectionDto.getSchoolId());
                section.setSchool(school);
            }
            sectionRepository.save(section);
            return "Section with Id: " + id + "Successfully Updated";
        }
        throw new EntityException.NotFound("section");
    }

    @Override
    public String deleteSection(Long id) {
        if (id != null && sectionRepository.existsById(id)) {
            sectionRepository.deleteById(id);
            return "Section with ID: " + id + "Successfully Deleted";
        }
        throw new EntityException.NotFound("section");
    }
}
