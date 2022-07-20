package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.SubjectDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.SubjectMapper;
import com.transkript.reportcard.business.service.interf.SectionService;
import com.transkript.reportcard.business.service.interf.SubjectService;
import com.transkript.reportcard.data.entity.Section;
import com.transkript.reportcard.data.entity.Subject;
import com.transkript.reportcard.data.repository.SubjectRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final SectionService sectionService;

    @Override
    public List<SubjectDto> getSubjects() {

        return subjectRepository.findAll().stream()
                .map(subjectMapper::mapSubjectToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDto getSubject(Long id) {
        return subjectMapper.mapSubjectToDto(getSubjectEntity(id));
    }

    @Override
    public EntityResponse addSubject(SubjectDto subjectDto) {
        Subject subject = subjectMapper.mapDtoToSubject(subjectDto);
        Section section = sectionService.getSectionEntity(subjectDto.getSectionId());
        subject.setId(null);
        subject.setSection(section);
        return EntityResponse.builder().id(subjectRepository.save(subject).getId())
                .entityName("subject").message("subject added successfully").build();
    }

    @Override
    public EntityResponse updateSubject(Long id, SubjectDto subjectDto) {
        if (id != null && subjectRepository.existsById(id) && id.equals(subjectDto.getId())) {
            Subject subject = subjectRepository.getById(id);
            if (subjectDto.getName() != null & !Objects.equals(subjectDto.getName(), subject.getName())) {
                subject.setName(subjectDto.getName());
            }
            if (subjectDto.getCode() != null & !Objects.equals(subjectDto.getCode(), subject.getCode())) {
                subject.setCode(subjectDto.getCode());
            }
            if (subjectDto.getCoefficient() != null && !Objects.equals(subjectDto.getCoefficient(), subject.getCoefficient())) {
                subject.setCoefficient(subjectDto.getCoefficient());
            }
            if (subjectDto.getSectionId() != null && !Objects.equals(subjectDto.getSectionId(), subject.getSection().getId())) {
                Section section = sectionService.getSectionEntity(subjectDto.getSectionId());
                subject.setSection(section);
            }
            return EntityResponse.builder().id(subjectRepository.save(subject).getId())
                    .entityName("subject").message("subject updated successfully").build();
        }
        throw new EntityException.NotFound("subject");
    }

    @Override
    public String deleteSubject(Long id) {
        if (id != null && subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
            return "Subject with ID: " + id + "Successfully Deleted";
        }
        throw new EntityException.NotFound("subject");
    }

    @Override
    public Subject getSubjectEntity(Long subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow(
                () -> new EntityException.NotFound("subject", subjectId)
        );
    }
}
