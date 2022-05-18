package com.transkript.reportcard.business.service.impl;
import com.sun.xml.bind.v2.TODO;
import com.transkript.reportcard.api.dto.SchoolDto;
import com.transkript.reportcard.business.mapper.SchoolMapper;
import com.transkript.reportcard.business.service.SchoolService;
import com.transkript.reportcard.data.entity.School;
import com.transkript.reportcard.data.repository.SchoolRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter @Setter
@AllArgsConstructor
@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    @Override
    public School getSchoolEntity(Long id) {
        return schoolRepository.findById(id).orElseThrow(()->{
            throw new EntityException.EntityNotFoundException("school", id);
        });
    }

    @Override
    public String addSchool(SchoolDto schoolDto) {
        School school = schoolMapper.mapDtoToSchool(schoolDto);
        school.setId(null);
        schoolRepository.save(school);
        return "School Successfully created";
    }

    @Override
    public List<SchoolDto> getSchools() {
        return schoolRepository.findAll().stream()
                .map(schoolMapper::mapSchoolToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SchoolDto getSchool(Long id) {
        Optional<School> schoolOptional = schoolRepository.findById(id);

        return schoolMapper.mapSchoolToDto(schoolOptional.orElseThrow(()->{

            //TODO: Handle this exception
            throw new RuntimeException();
        }));

    }

    @Override
    public String updateSchool(Long id, SchoolDto schoolDto) {
        if(id != null && schoolRepository.existsById(id)){
            School school = schoolMapper.mapDtoToSchool(schoolDto);
            school.setId(id);
            schoolRepository.save(school);
            return "school with id: "+ id + "successfully updated";
        }
        throw new EntityException.EntityNotFoundException("School");
    }

    @Override
    public String deleteSchool(Long id) {
        if(id != null && schoolRepository.existsById(id)){
            schoolRepository.deleteById(id);
            return "School with ID: " + id + "successfully deleted";
        }
        throw  new EntityException.EntityNotFoundException("school");
    }
}
