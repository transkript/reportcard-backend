package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.SectionMapper;
import com.transkript.reportcard.business.service.i.SchoolService;
import com.transkript.reportcard.business.service.i.SectionService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.config.constants.ResponseMessage;
import com.transkript.reportcard.data.entity.School;
import com.transkript.reportcard.data.entity.Section;
import com.transkript.reportcard.data.repository.SectionRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
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
    private final String entityName = EntityName.SECTION;
    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final SchoolService schoolService;

    @NotNull
    @Override
    public Section getEntity(long id) {
        return sectionRepository.findById(id).orElseThrow(() -> {
            throw new EntityException.NotFound("section");
        });

    }

    @NotNull
    @Override
    public EntityResponse create(@NotNull SectionDto sectionDto) {
        Section section = sectionMapper.mapDtoToSection(sectionDto);
        School school = schoolService.getEntity(sectionDto.getSchoolId());
        section.setId(null);
        section.setSchool(school);
        section = sectionRepository.save(section);
        return new EntityResponse(section.getId(), ResponseMessage.SUCCESS.created(entityName), true);
    }

    @NotNull
    @Override
    public List<SectionDto> getAllDto() {
        return sectionRepository.findAll().stream()
                .map(sectionMapper::mapSectionToDto)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    public SectionDto getDto(long id) {
        Optional<Section> sectionOptional = sectionRepository.findById(id);
        return sectionMapper.mapSectionToDto(sectionOptional.orElseThrow(() -> {
            throw new EntityException.NotFound("section");
        }));
    }

    @NotNull
    @Override
    public EntityResponse update(@NotNull SectionDto sectionDto) {
        Section section = getEntity(sectionDto.getId());
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
        section = sectionRepository.save(section);
        return new EntityResponse(section.getId(), ResponseMessage.SUCCESS.updated(entityName), true);
    }

    @Override
    public void delete(long id) {
        sectionRepository.deleteById(id);
    }

    @NotNull
    @Override
    public List<SectionDto> getDtoBySchoolId(long schoolId) {
        School school = schoolService.getEntity(schoolId);
        return sectionRepository.findAllBySchool(school).stream().map(sectionMapper::mapSectionToDto).toList();
    }
}
