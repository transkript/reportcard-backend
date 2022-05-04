package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.SectionDto;
import com.transkript.reportcard.data.entity.Section;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface SectionMapper {

	SectionDto mapSectionToDto(Section section);

	Section mapDtoToSection(SectionDto sectionDto);

}
