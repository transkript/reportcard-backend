package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.ClassLevelDto;
import com.transkript.reportcard.data.entity.ClassLevel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface ClassLevelMapper {

	ClassLevelDto mapClassLevelToDto(ClassLevel classlevel);

	ClassLevel mapDtoToClassLevel(ClassLevelDto classlevelDto);

}
