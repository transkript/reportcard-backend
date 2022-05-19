package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.ClassLevelDto;
import com.transkript.reportcard.data.entity.ClassLevel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface ClassLevelMapper {

    @Mappings({
            @Mapping(target = "sectionId", expression = "java(classLevel.getSection().getId())"),
    })
    ClassLevelDto mapClassLevelToDto(ClassLevel classLevel);

    @Mappings({
            @Mapping(target = "section", ignore = true),
            @Mapping(target = "classLevelSubs", ignore = true),
    })
    @InheritInverseConfiguration
    ClassLevel mapDtoToClassLevel(ClassLevelDto classlevelDto);

}
