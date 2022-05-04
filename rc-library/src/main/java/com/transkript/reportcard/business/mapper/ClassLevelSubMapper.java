package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.ClassLevelSubDto;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import org.mapstruct.Mapper;

// @Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface ClassLevelSubMapper {

	ClassLevelSubDto mapClassLevelSubToDto(ClassLevelSub classlevelsub);

	ClassLevelSub mapDtoToClassLevelSub(ClassLevelSubDto classlevelsubDto);

}
