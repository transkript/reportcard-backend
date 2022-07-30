package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.ClassLevelSubDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.ClassLevelSubMapper;
import com.transkript.reportcard.business.service.i.ClassLevelService;
import com.transkript.reportcard.business.service.i.ClassLevelSubService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.config.constants.ResponseMessage;
import com.transkript.reportcard.data.entity.ClassLevel;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import com.transkript.reportcard.data.repository.ClassLevelSubRepository;
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
public class ClassLevelSubServiceImpl implements ClassLevelSubService {
    private final String entityName = EntityName.CLASS_LEVEL_SUB;
    ClassLevelSubRepository classLevelSubRepository;
    ClassLevelSubMapper classLevelSubMapper;
    ClassLevelService classLevelService;

    @Override
    public EntityResponse addClassLevelSub(ClassLevelSubDto classLevelSubDto) {
        ClassLevel classLevel = classLevelService.getClassLevelEntity(classLevelSubDto.getClassLevelId());

        if (classLevelSubRepository.findByNameAndClassLevel(classLevelSubDto.getName(), classLevel).isPresent()) {
            throw new EntityException.AlreadyExists(
                    EntityName.CLASS_LEVEL_SUB,
                    String.format("%s -> %s", classLevel.getName(), classLevelSubDto.getName())
            );
        }

        ClassLevelSub classLevelSub = classLevelSubMapper.mapDtoToClassLevelSub(classLevelSubDto);
        classLevelSub.setId(null);
        classLevelSub.setClassLevel(classLevel);
        classLevelSub = classLevelSubRepository.save(classLevelSub);
        return new EntityResponse(classLevelSub.getId(), ResponseMessage.SUCCESS.created(entityName), true);

    }

    @Override
    public List<ClassLevelSubDto> getClassLevelSubs() {
        return classLevelSubRepository.findAll().stream()
                .map(classLevelSub -> getClassLevelSubMapper().mapClassLevelSubToDto(classLevelSub))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClassLevelSubDto> getClassLevelSubsByClassLevel(Long levelId) {
        ClassLevel classLevel = classLevelService.getClassLevelEntity(levelId);
        return classLevelSubRepository.findAllByClassLevel(classLevel).stream()
                .map(classLevelSubMapper::mapClassLevelSubToDto).toList();
    }

    @Override
    public ClassLevelSubDto getClassLevelSub(Long id) {
        return classLevelSubMapper.mapClassLevelSubToDto(getClassLevelSubEntity(id));
    }

    @Override
    public EntityResponse updateClassLevelSub(Long id, ClassLevelSubDto classLevelSubDto) {
        if (id != null && classLevelSubRepository.existsById(id) && id.equals(classLevelSubDto.getId())) {
            ClassLevel classLevel = classLevelService.getClassLevelEntity(classLevelSubDto.getClassLevelId());

            if (classLevelSubRepository.findByNameAndClassLevel(classLevelSubDto.getName(), classLevel).isPresent()) {
                throw new EntityException.AlreadyExists(
                        EntityName.CLASS_LEVEL_SUB,
                        String.format("%s -> %s", classLevel.getName(), classLevelSubDto.getName())
                );
            }

            ClassLevelSub classLevelSub = classLevelSubMapper.mapDtoToClassLevelSub(classLevelSubDto);
            classLevelSub.setId(id);
            classLevelSub.setClassLevel(classLevel);
            classLevelSubRepository.save(classLevelSub);
            return new EntityResponse(classLevelSub.getId(), ResponseMessage.SUCCESS.created(entityName), true);
        }
        throw new EntityException.NotFound("class level" + id);
    }

    @Override
    public void deleteClassLevelSub(Long id) {
        if (id != null && classLevelSubRepository.existsById(id)) {
            classLevelSubRepository.deleteById(id);
            return;
        }
        throw new EntityException.NotFound("class level" + id);
    }

    @Override
    public ClassLevelSub getClassLevelSubEntity(Long id) {
        return classLevelSubRepository.findById(id).orElseThrow(() -> {
            throw new EntityException.NotFound("class level" + id);
        });
    }
}
