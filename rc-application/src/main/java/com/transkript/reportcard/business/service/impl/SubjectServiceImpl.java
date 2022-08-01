package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.SubjectDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.SubjectMapper;
import com.transkript.reportcard.business.service.i.SectionService;
import com.transkript.reportcard.business.service.i.SubjectService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.config.constants.ResponseMessage;
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
    private final String entityName = EntityName.SUBJECT;

    public List<SubjectDto> getAllDto() {

        return subjectRepository.findAll().stream()
                .map(subjectMapper::subjectToSubjectDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDto getDto(Long id) {
        return subjectMapper.subjectToSubjectDto(getEntity(id));
    }

    @Override
    public EntityResponse create(SubjectDto subjectDto) {
        Subject subject = subjectMapper.subjectDtoToSubject(subjectDto);
        Section section = sectionService.getEntity(subjectDto.sectionId());
        subject.setId(null);
        subject.setSection(section);

        subject = subjectRepository.save(subject);
        return new EntityResponse(subject.getId(), ResponseMessage.SUCCESS.created(entityName), true);
    }

    @Override
    public EntityResponse update(SubjectDto subjectDto) {
        Subject subject = getEntity(subjectDto.id());
        subject.setName(subjectDto.name());
        subject.setCode(subjectDto.code());
        subject.setCoefficient(subjectDto.coefficient());
        if (!Objects.equals(subjectDto.sectionId(), subject.getSection().getId())) {
            Section section = sectionService.getEntity(subjectDto.sectionId());
            subject.setSection(section);
        }
        subject = subjectRepository.save(subject);
        return new EntityResponse(subject.getId(), ResponseMessage.SUCCESS.updated(entityName), true);
    }

    @Override
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public Subject getEntity(Long id) {
        return subjectRepository.findById(id).orElseThrow(
                () -> new EntityException.NotFound("subject", id)
        );
    }
}
