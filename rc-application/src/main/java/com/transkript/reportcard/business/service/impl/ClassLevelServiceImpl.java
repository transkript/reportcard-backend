package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.ClassLevelDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.ClassLevelMapper;
import com.transkript.reportcard.business.service.i.ClassLevelService;
import com.transkript.reportcard.business.service.i.SectionService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.config.constants.ResponseMessage;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Service
public class ClassLevelServiceImpl implements ClassLevelService {
    private final String entityName = EntityName.CLASS_LEVEL;
    private final ClassLevelRepository classLevelRepository;
    private final ClassLevelMapper classLevelMapper;
    private final SectionRepository sectionRepository;
    private final SectionService sectionService;

    @Override
    public EntityResponse create(ClassLevelDto classLevelDto) {


        Optional<ClassLevel> optionalClassLevel = classLevelRepository.findByName(classLevelDto.getName());

        if (optionalClassLevel.isPresent()) {
            throw new EntityException.AlreadyExists("class level", classLevelDto.getName());
        }

        ClassLevel classLevel = classLevelMapper.mapDtoToClassLevel(classLevelDto);

        Section section = sectionRepository.findById(classLevelDto.getSectionId()).orElseThrow(
                () -> new EntityException.NotFound("section with id: " +
                        classLevelDto.getSectionId())
        );
        classLevel.setId(null);
        classLevel.setSection(section);
        classLevel = classLevelRepository.save(classLevel);
        return new EntityResponse(classLevel.getId(), ResponseMessage.SUCCESS.created(entityName), true);
    }

    @Override
    public List<ClassLevelDto> getAllDtos() {
        return classLevelRepository.findAll().stream()
                .map(classLevelMapper::mapClassLevelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClassLevelDto> getAllDtosBySection(Long sectionId) {
        Section section = sectionService.getEntity(sectionId);
        return classLevelRepository.findAllBySection(section).stream().map(classLevelMapper::mapClassLevelToDto).toList();
    }

    @Override
    public ClassLevelDto getDto(Long id) {
        return classLevelMapper.mapClassLevelToDto(getEntity(id));
    }

    @Override
    public ClassLevel getEntity(Long id) {
        return classLevelRepository.findById(id).orElseThrow(() -> {
            throw new EntityException.NotFound("class level", id);
        });
    }

    @Override
    public EntityResponse update(ClassLevelDto classLevelDto) {
        ClassLevel classLevel = getEntity(classLevelDto.getId());
        Section existingSection = sectionService.getEntity(classLevelDto.getId());

        if (!Objects.equals(classLevelDto.getName(), classLevel.getName())) {
            if (classLevelRepository.findByName(classLevelDto.getName()).isPresent()) {
                throw new EntityException.AlreadyExists(EntityName.CLASS_LEVEL, classLevelDto.getName());
            }
            classLevel.setName(classLevelDto.getName());
        }
        if (!Objects.equals(classLevelDto.getSectionId(), existingSection.getId())) {
            Section section = sectionService.getEntity(classLevelDto.getSectionId());
            classLevel.setSection(section);
        }
        classLevel = classLevelRepository.save(classLevel);

        return new EntityResponse(classLevel.getId(), ResponseMessage.SUCCESS.created(entityName), true);
    }

    @Override
    public void delete(Long id) {
        if (id != null && classLevelRepository.existsById(id)) {
            classLevelRepository.deleteById(id);
            return;
        }
        throw new EntityException.NotFound("class level", id);
    }
}
