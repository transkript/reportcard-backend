package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.ClassLevelDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.ClassLevelMapper;
import com.transkript.reportcard.business.service.ClassLevelService;
import com.transkript.reportcard.business.service.SectionService;
import com.transkript.reportcard.data.entity.ClassLevel;
import com.transkript.reportcard.data.entity.Section;
import com.transkript.reportcard.data.repository.ClassLevelRepository;
import com.transkript.reportcard.data.repository.SectionRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Service
public class ClassLevelServiceImpl implements ClassLevelService {

    private final ClassLevelRepository classLevelRepository;
    private final ClassLevelMapper classLevelMapper;
    private final SectionRepository sectionRepository;
    private final SectionService sectionService;

    @Override
    public EntityResponse addClassLevel(ClassLevelDto classLevelDto) {
        ClassLevel classLevel = classLevelMapper.mapDtoToClassLevel(classLevelDto);
        Section section = sectionRepository.findById(classLevelDto.getSectionId()).orElseThrow(
                () -> new EntityException.EntityNotFoundException("section with id: " +
                        classLevelDto.getSectionId())
        );
        classLevel.setId(null);
        classLevel.setSection(section);
        classLevelRepository.save(classLevel);
        return EntityResponse.builder().message("Successfully saved Class level")
                .entityName("class level")
                .id(classLevelRepository.save(classLevel).getId()).build();
    }

    @Override
    public List<ClassLevelDto> getClassLevels() {
        return classLevelRepository.findAll().stream()
                .map(classLevelMapper::mapClassLevelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClassLevelDto> getClassLevels(Long sectionId) {
        Section section = sectionService.getSectionEntity(sectionId);
        return classLevelRepository.findAllBySection(section).stream().map(classLevelMapper::mapClassLevelToDto).toList();
    }

    @Override
    public ClassLevelDto getClassLevel(Long id) {
        return classLevelMapper.mapClassLevelToDto(getClassLevelEntity(id));
    }

    @Override
    public ClassLevel getClassLevelEntity(Long id) {
        return classLevelRepository.findById(id).orElseThrow(() -> {
            throw new EntityException.EntityNotFoundException("class level", id);
        });
    }

    @Override
    public EntityResponse updateClassLevel(Long id, ClassLevelDto classLevelDto) {
        if (id != null && classLevelRepository.existsById(id)) {
            ClassLevel classLevel = classLevelMapper.mapDtoToClassLevel(classLevelDto);
            Section section = sectionRepository.findById(classLevelDto.getSectionId())
                    .orElseThrow(() -> new EntityException.EntityNotFoundException("section", classLevelDto.getSectionId()));
            classLevel.setId(id);
            classLevel.setSection(section);
            classLevelRepository.save(classLevel);
            return EntityResponse.builder().message("Successfully updated class level")
                    .entityName("class level")
                    .id(classLevelRepository.save(classLevel).getId()).build();
        }
        throw new EntityException.EntityNotFoundException("class level", id);
    }

    @Override
    public void deleteClassLevel(Long id) {
        if (id != null && classLevelRepository.existsById(id)) {
            classLevelRepository.deleteById(id);
            return;
        }
        throw new EntityException.EntityNotFoundException("class level", id);
    }
}
