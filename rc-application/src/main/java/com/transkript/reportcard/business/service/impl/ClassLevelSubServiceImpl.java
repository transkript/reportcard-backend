package com.transkript.reportcard.business.service.impl;
import com.transkript.reportcard.api.dto.ClassLevelSubDto;
import com.transkript.reportcard.business.mapper.ClassLevelSubMapper;
import com.transkript.reportcard.business.service.ClassLevelService;
import com.transkript.reportcard.business.service.ClassLevelSubService;
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

@Getter @Setter
@AllArgsConstructor
@Service
public class ClassLevelSubServiceImpl implements ClassLevelSubService {

    ClassLevelSubRepository classLevelSubRepository;
    ClassLevelSubMapper classLevelSubMapper;
    ClassLevelService classLevelService;

    @Override
    public String addClassLevelSub(ClassLevelSubDto classLevelSubDto) {
        ClassLevelSub classLevelSub = classLevelSubMapper.mapDtoToClassLevelSub(classLevelSubDto);
        ClassLevel classLevel = classLevelService.getClassLevelById(classLevelSubDto.getClassLevelId());
        classLevelSub.setId(null);
        classLevelSub.setClassLevel(classLevel);
        classLevelSubRepository.save(classLevelSub);
        return "Successfully saved class level sub with Name: " + classLevel.getName();
    }

    @Override
    public List<ClassLevelSubDto> getClassLevelSubs() {
        return classLevelSubRepository.findAll().stream()
                .map(classLevelSub -> getClassLevelSubMapper().mapClassLevelSubToDto(classLevelSub))
                .collect(Collectors.toList());
    }

    @Override
    public ClassLevelSubDto getClassLevelSub(Long id) {
        return classLevelSubMapper.mapClassLevelSubToDto(
                classLevelSubRepository.findById(id).orElseThrow(
                        ()-> {
                            throw new EntityException.EntityNotFoundException("Class level sub with id: " + id);
                        }
                )
        );
    }

    @Override
    public String updateClassLevelSub(Long id, ClassLevelSubDto classLevelSubDto) {
        if (id != null && classLevelSubRepository.existsById(id)) {
            ClassLevelSub classLevelSub = classLevelSubMapper.mapDtoToClassLevelSub(classLevelSubDto);
            ClassLevel classLevel = classLevelService.getClassLevelById(classLevelSubDto.getClassLevelId());
            classLevelSub.setId(id);
            classLevelSub.setClassLevel(classLevel);
            classLevelSubRepository.save(classLevelSub);
            return "Successfully updated class level sub with id: " + id;
        }
        throw new EntityException.EntityNotFoundException("Class level sub with id: " + id);
    }

    @Override
    public String deleteClassLevelSub(Long id) {
        if (id != null && classLevelSubRepository.existsById(id)) {
            classLevelSubRepository.deleteById(id);
            return "Successfully deleted class level sub with id: " + id;
        }
        throw new EntityException.EntityNotFoundException("Class level sub with id: " + id);
    }
}
