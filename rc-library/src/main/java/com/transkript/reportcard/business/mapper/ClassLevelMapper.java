package com.transkript.reportcard.business.mapper;


@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface ClassLevelMapper {

	ClassLevelDto mapClassLevelToDto(ClassLevel classlevel);

	ClassLevel mapDtoToClassLevel(ClassLevelDto classlevelDto);

}
