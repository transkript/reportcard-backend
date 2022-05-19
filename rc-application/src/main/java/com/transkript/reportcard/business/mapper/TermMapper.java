package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.TermDto;
import com.transkript.reportcard.data.entity.Term;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface TermMapper {

    @Mappings({@Mapping(target = "academicYearId", expression = "java(term.getAcademicYear().getId())")})
    TermDto mapTermToDto(Term term);

    @Mappings({
            @Mapping(target = "academicYear", ignore = true),
            @Mapping(target = "sequences", ignore = true),
    })
    @InheritInverseConfiguration
    Term mapDtoToTerm(TermDto termDto);

}
