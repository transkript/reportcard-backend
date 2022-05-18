package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.SequenceDto;

import java.util.List;

public interface SequenceService {
    String addSequence(SequenceDto sequenceDto);

    List<SequenceDto> getSequences();

    SequenceDto getSequence(Long id);

    String updateSequence(Long id, SequenceDto sequenceDto);

    String deleteSequence(Long id);
}
