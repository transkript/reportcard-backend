package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.AcademicYearDto;
import com.transkript.reportcard.data.entity.AcademicYear;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface AcademicYearMapper {

	AcademicYearDto mapAcademicYearToDto(AcademicYear academicyear);

	AcademicYear mapDtoToAcademicYear(AcademicYearDto academicyearDto);

}
