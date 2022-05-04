package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.SequenceDto;
import com.transkript.reportcard.data.entity.Sequence;
import org.mapstruct.Mapper;

// @Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface SequenceMapper {

	SequenceDto mapSequenceToDto(Sequence sequence);

	Sequence mapDtoToSequence(SequenceDto sequenceDto);

}
