package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.TermDto;
import com.transkript.reportcard.data.entity.Term;
import org.mapstruct.Mapper;

// @Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface TermMapper {

	TermDto mapTermToDto(Term term);

	Term mapDtoToTerm(TermDto termDto);

}
