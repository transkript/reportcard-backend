package com.transkript.reportcard.business.service;


import com.transkript.reportcard.api.dto.SequenceDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.data.entity.Sequence;

import java.util.List;

public interface SequenceService {
    EntityResponse addSequence(SequenceDto sequenceDto);

    List<SequenceDto> getSequences();

    SequenceDto getSequence(Long id);

    EntityResponse updateSequence(Long id, SequenceDto sequenceDto);

    String deleteSequence(Long id);

    Sequence getSequenceEntity(Long sequenceId);
}
