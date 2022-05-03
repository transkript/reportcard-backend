package com.transkript.reportcard.business.mapper;


@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface SectionMapper {

	SectionDto mapSectionToDto(Section section);

	Section mapDtoToSection(SectionDto sectionDto);

}
