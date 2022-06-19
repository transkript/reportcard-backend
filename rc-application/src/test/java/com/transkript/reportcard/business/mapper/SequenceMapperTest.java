package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.TestDefaults;
import com.transkript.reportcard.api.dto.SequenceDto;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.Term;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SequenceMapperTest {
    private final SequenceMapper sequenceMapper;
    Sequence testSequence;
    SequenceDto testSequenceDto;
    Term testTerm;

    @Autowired
    SequenceMapperTest(SequenceMapper sequenceMapper) {
        this.sequenceMapper = sequenceMapper;
    }

    @Test
    void mapSequenceToDto() {
        testTerm = new Term();
        testTerm.setId(4L);

        testSequence = Sequence.builder()
                .id(1L)
                .name("First")
                .term(testTerm)
                .build();

        SequenceDto expectedDto = this.sequenceMapper.mapSequenceToDto(testSequence);

        Assertions.assertEquals(expectedDto.id(), testSequence.getId());
        Assertions.assertEquals(expectedDto.name(), testSequence.getName());
        Assertions.assertEquals(expectedDto.termId(), testSequence.getTerm().getId());
    }

    @Test
    void mapDtoToSequence() {
        testSequenceDto = TestDefaults.SEQUENCE_DTO;

        Sequence expectedSequence = this.sequenceMapper.mapDtoToSequence(testSequenceDto);

        Assertions.assertEquals(expectedSequence.getId(), testSequenceDto.id());
        Assertions.assertEquals(expectedSequence.getName(), testSequenceDto.name());
    }
}
