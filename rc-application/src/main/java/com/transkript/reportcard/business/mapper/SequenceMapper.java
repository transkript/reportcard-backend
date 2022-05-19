package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.SequenceDto;
import com.transkript.reportcard.data.entity.Sequence;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface SequenceMapper {
    @Mappings({
            @Mapping(
                    target = "termId",
                    expression = "java(sequence.getTerm().getId())"
            )
    })
    SequenceDto mapSequenceToDto(Sequence sequence);

    @Mappings({
            @Mapping(target = "term", ignore = true)
    })
    @InheritInverseConfiguration
    Sequence mapDtoToSequence(SequenceDto sequenceDto);

}
