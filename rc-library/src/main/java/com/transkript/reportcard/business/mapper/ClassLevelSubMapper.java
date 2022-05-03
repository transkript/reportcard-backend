package com.transkript.reportcard.business.mapper;


@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface ClassLevelSubMapper {

	ClassLevelSubDto mapClassLevelSubToDto(ClassLevelSub classlevelsub);

	ClassLevelSub mapDtoToClassLevelSub(ClassLevelSubDto classlevelsubDto);

}
