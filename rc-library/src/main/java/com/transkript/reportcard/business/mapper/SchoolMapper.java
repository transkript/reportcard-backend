package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.SchoolDto;
import com.transkript.reportcard.data.entity.School;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface SchoolMapper {

	SchoolDto mapSchoolToDto(School school);

	School mapDtoToSchool(SchoolDto schoolDto);

}
