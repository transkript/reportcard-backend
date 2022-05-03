package com.transkript.reportcard.business.mapper;


@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface TermMapper {

	TermDto mapTermToDto(Term term);

	Term mapDtoToTerm(TermDto termDto);

}
