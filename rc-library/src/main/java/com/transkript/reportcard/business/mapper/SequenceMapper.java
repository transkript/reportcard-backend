package com.transkript.reportcard.business.mapper;


@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface SequenceMapper {

	SequenceDto mapSequenceToDto(Sequence sequence);

	Sequence mapDtoToSequence(SequenceDto sequenceDto);

}
