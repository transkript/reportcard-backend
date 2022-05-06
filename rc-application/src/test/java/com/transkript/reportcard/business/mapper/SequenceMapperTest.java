package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.api.dto.SequenceDto;
import com.transkript.reportcard.data.entity.Section;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.Term;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SequenceMapperTest {
    Sequence testSequence;
    SequenceDto testSequenceDto;
    Term testTerm;

    private final SequenceMapper sequenceMapper;

    @Autowired
    SequenceMapperTest(SequenceMapper sequenceMapper) {
        this.sequenceMapper = sequenceMapper;
    }

    @BeforeEach
    void setup() {
        testTerm = new Term();
        testTerm.setName("First");

        testSequence = Sequence.builder()
                .id(1L)
                .name("First")
                .term(testTerm)
                .build();

        testSequenceDto = SequenceDto.builder()
                .id(2L)
                .name("Second")
                .build();
    }

    @Test
    void mapSequenceToDto() {
        SequenceDto expectedDto = this.sequenceMapper.mapSequenceToDto(testSequence);

        Assertions.assertEquals(expectedDto.getId(), testSequence.getId());
    }

    @Test
    void mapDtoToSequence() {
        Sequence expectedSequence = this.sequenceMapper.mapDtoToSequence(testSequenceDto);

        Assertions.assertEquals(expectedSequence.getId(), testSequenceDto.getId());
    }
}