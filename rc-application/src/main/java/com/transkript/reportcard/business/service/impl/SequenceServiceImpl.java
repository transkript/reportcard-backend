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
import java.util.Objects;
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
        sequence.setTerm(termService.getTermEntity(sequenceDto.termId()));
        sequence = sequenceRepository.save(sequence);
        return new EntityResponse(sequence.getId(), ResponseMessage.SUCCESS.updated(entityName), true);
    }

    @Override
    public List<SequenceDto> getSequences() {
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
    public EntityResponse update(Long id, SequenceDto sequenceDto) {
        if (id != null && id.equals(sequenceDto.id()) && sequenceRepository.existsById(id)) {
            Sequence sequence = sequenceRepository.getById(id);
            {
                if (!Objects.equals(sequence.getName(), sequenceDto.name())) {
                    sequence.setName(sequenceDto.name());
                }
                if (!Objects.equals(sequence.getTerm().getId(), sequenceDto.termId())) {
                    sequence.setTerm(termService.getTermEntity(sequenceDto.termId()));
                }
            }
            sequence = sequenceRepository.save(sequence);
            return new EntityResponse(sequence.getId(), ResponseMessage.SUCCESS.updated(entityName), true);
        }
        throw new EntityException.NotFound("Sequence with id: " + id);
    }

    @Override
    public String delete(Long id) {
        if (id != null && sequenceRepository.existsById(id)) {
            sequenceRepository.deleteById(id);
            return "Successfully deleted Sequence with id: " + id;
        }
        throw new EntityException.NotFound("Sequence with id " + id);
    }

    @NotNull
    @Override
    public Sequence getEntity(Long sequenceId) {
        return sequenceRepository.findById(sequenceId).orElseThrow(() -> new EntityException.NotFound("sequence", sequenceId));
    }
}
