package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.SequenceDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.SequenceMapper;
import com.transkript.reportcard.business.service.i.SequenceService;
import com.transkript.reportcard.business.service.i.TermService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.config.constants.ResponseMessage;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.repository.SequenceRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Service
public class SequenceServiceImpl implements SequenceService {

    private final SequenceRepository sequenceRepository;
    private final SequenceMapper sequenceMapper;
    private final TermService termService;
    private final String entityName = EntityName.SEQUENCE;

    @Override
    public EntityResponse create(SequenceDto sequenceDto) {
        Sequence sequence = sequenceMapper.mapDtoToSequence(sequenceDto);
        sequence.setId(null);
        sequence.setTerm(termService.getEntity(sequenceDto.termId()));
        sequence = sequenceRepository.save(sequence);
        return new EntityResponse(sequence.getId(), ResponseMessage.SUCCESS.updated(entityName), true);
    }

    @Override
    public List<SequenceDto> getAllDto() {
        return sequenceRepository.findAll().stream()
                .map(sequenceMapper::mapSequenceToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SequenceDto getDto(Long id) {
        return sequenceMapper.mapSequenceToDto(
                sequenceRepository.findById(id).orElseThrow(
                        () -> {
                            throw new EntityException.NotFound("Sequence with id " + id);
                        }
                )
        );
    }

    @Override
    public EntityResponse update(SequenceDto sequenceDto) {
        Sequence sequence = getEntity(sequenceDto.id());
        sequence.setName(sequenceDto.name());
        sequence.setTerm(termService.getEntity(sequenceDto.termId()));
        sequence = sequenceRepository.save(sequence);
        return new EntityResponse(sequence.getId(), ResponseMessage.SUCCESS.updated(entityName), true);
    }

    @Override
    public void delete(Long id) {
        sequenceRepository.deleteById(id);
    }

    @NotNull
    @Override
    public Sequence getEntity(Long sequenceId) {
        return sequenceRepository.findById(sequenceId).orElseThrow(() -> new EntityException.NotFound("sequence", sequenceId));
    }
}
