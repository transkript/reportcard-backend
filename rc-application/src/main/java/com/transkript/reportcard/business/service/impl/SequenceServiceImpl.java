package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.SequenceDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.SequenceMapper;
import com.transkript.reportcard.business.service.i.SequenceService;
import com.transkript.reportcard.business.service.i.TermService;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.repository.SequenceRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
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

    @Override
    public EntityResponse addSequence(SequenceDto sequenceDto) {
        Sequence sequence = sequenceMapper.mapDtoToSequence(sequenceDto);
        sequence.setId(null);
        sequence.setTerm(termService.getTermEntity(sequenceDto.termId()));
        sequence = sequenceRepository.save(sequence);
        return EntityResponse.builder().id(sequence.getId()).entityName("sequence").message("Successfully created sequence " + sequence.getName()).build();
    }

    @Override
    public List<SequenceDto> getSequences() {
        return sequenceRepository.findAll().stream()
                .map(sequenceMapper::mapSequenceToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SequenceDto getSequence(Long id) {
        return sequenceMapper.mapSequenceToDto(
                sequenceRepository.findById(id).orElseThrow(
                        () -> {
                            throw new EntityException.NotFound("Sequence with id " + id);
                        }
                )
        );
    }

    @Override
    public EntityResponse updateSequence(Long id, SequenceDto sequenceDto) {
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
            return EntityResponse.builder().id(sequence.getId()).entityName("sequence").message("Successfully updated sequence " + sequence.getName()).build();
        }
        throw new EntityException.NotFound("Sequence with id: " + id);
    }

    @Override
    public String deleteSequence(Long id) {
        if (id != null && sequenceRepository.existsById(id)) {
            sequenceRepository.deleteById(id);
            return "Successfully deleted Sequence with id: " + id;
        }
        throw new EntityException.NotFound("Sequence with id " + id);
    }

    @Override
    public Sequence getSequenceEntity(Long sequenceId) {
        return sequenceRepository.findById(sequenceId).orElseThrow(
                () -> new EntityException.NotFound("sequence", sequenceId)
        );
    }
}
