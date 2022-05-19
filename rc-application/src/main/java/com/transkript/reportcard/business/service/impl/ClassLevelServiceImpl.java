package com.transkript.reportcard.business.service.impl;
import com.transkript.reportcard.api.dto.ClassLevelDto;
import com.transkript.reportcard.business.mapper.ClassLevelMapper;
import com.transkript.reportcard.business.service.ClassLevelService;
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

@Getter @Setter
@AllArgsConstructor
@Service
public class ClassLevelServiceImpl implements ClassLevelService {

    private final ClassLevelRepository classLevelRepository;
    private final ClassLevelMapper classLevelMapper;
    private final SectionRepository sectionRepository;

    @Override
    public String addClassLevel(ClassLevelDto classLevelDto) {
        ClassLevel classLevel = classLevelMapper.mapDtoToClassLevel(classLevelDto);
        Section section = sectionRepository.findById(classLevelDto.getSectionId()).orElseThrow(
                ()->new EntityException.EntityNotFoundException("section with id: " +
                        classLevelDto.getSectionId())
        );
        classLevel.setId(null);
        classLevel.setSection(section);
        classLevelRepository.save(classLevel);
        return "Successfully saved Class level with Name: " + classLevel.getName();
    }

    @Override
    public List<ClassLevelDto> getClassLevels() {
        return classLevelRepository.findAll().stream()
                .map(classLevelMapper::mapClassLevelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClassLevelDto getClassLevel(Long id) {
        return classLevelMapper.mapClassLevelToDto(getClassLevelEntity(id));
    }

    @Override
    public ClassLevel getClassLevelEntity(Long id) {
        return classLevelRepository.findById(id).orElseThrow(() -> {
            throw new EntityException.EntityNotFoundException("Class level with id: " + id);
        });
    }

    @Override
    public String updateClassLevel(Long id, ClassLevelDto classLevelDto) {
        if (id != null && classLevelRepository.existsById(id)) {
            ClassLevel classLevel = classLevelMapper.mapDtoToClassLevel(classLevelDto);
            Section section = sectionRepository.findById(classLevelDto.getSectionId()).orElseThrow(
                    ()->new EntityException.EntityNotFoundException("section with id: " +
                            classLevelDto.getSectionId())
            );
            classLevel.setId(null);
            classLevel.setSection(section);
            classLevelRepository.save(classLevel);
            return "Successfully updated class level with ID: " + id;
        }
        throw new EntityException.EntityNotFoundException("Class level with id: " + id);
    }

    @Override
    public String deleteClassLevel(Long id) {
        if (id != null && classLevelRepository.existsById(id)) {
            classLevelRepository.deleteById(id);
            return "Successfully deleted class level with ID: " + id;
        }
        throw new EntityException.EntityNotFoundException("Class level with id " + id);
    }
}
