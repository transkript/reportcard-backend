package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.ClassLevelSubDto;
import com.transkript.reportcard.data.entity.ClassLevelSub;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface ClassLevelSubMapper {

	@Mappings({
			@Mapping(target = "classLevelId", expression = "java(classLevelSub.getClassLevel().getId())"),
	})
	ClassLevelSubDto mapClassLevelSubToDto(ClassLevelSub classLevelSub);

	@Mappings({
			@Mapping(target = "classLevel", ignore = true),
	})
	@InheritInverseConfiguration
	ClassLevelSub mapDtoToClassLevelSub(ClassLevelSubDto classlevelsubDto);
}
