package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.SequenceDto;
import com.transkript.reportcard.business.mapper.SequenceMapper;
import com.transkript.reportcard.business.service.SequenceService;
import com.transkript.reportcard.business.service.TermService;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.Term;
import com.transkript.reportcard.data.repository.SequenceRepository;
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
public class SequenceServiceImpl implements SequenceService {

    private final SequenceRepository sequenceRepository;
    private final SequenceMapper sequenceMapper;
    private final TermService termService;

    @Override
    public String addSequence(SequenceDto sequenceDto) {
        Sequence sequence = sequenceMapper.mapDtoToSequence(sequenceDto);
        sequence.setId(null);
        sequence.setTerm(termService.getTermById(sequenceDto.getTermId()));
        sequenceRepository.save(sequence);
        return "Sequence created successfully";
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
                            throw new EntityException.EntityNotFoundException("Sequence with id " + id);
                        }
                )
        );
    }

    @Override
    public String updateSequence(Long id, SequenceDto sequenceDto) {
        if (id != null && sequenceRepository.existsById(id)) {
            Sequence sequence = sequenceMapper.mapDtoToSequence(sequenceDto);
            sequence.setId(id);
            Term term = termService.getTermById(sequenceDto.getTermId());
            sequence.setTerm(term);
            return "Successfully updated sequence with id: " + id;
        }
        throw new EntityException.EntityNotFoundException("Sequence with id: " + id);
    }

    @Override
    public String deleteSequence(Long id) {
        if (id != null && sequenceRepository.existsById(id)) {
            sequenceRepository.deleteById(id);
            return "Successfully deleted Sequence with id: " + id;
        }
        throw new EntityException.EntityNotFoundException("Sequence with id " + id);
    }

    @Override
    public Sequence getSequenceEntity(Long sequenceId) {
       return sequenceRepository.findById(sequenceId).orElseThrow(
               () -> new EntityException.EntityNotFoundException("sequence", sequenceId)
       );
    }
}
