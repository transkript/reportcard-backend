package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.AcademicYearDto;
import com.transkript.reportcard.data.entity.AcademicYear;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface AcademicYearMapper {

    @Mappings({
            @Mapping(target = "numberOfTerms", expression = "java(academicYear.getTerms().size())")
    })
    AcademicYearDto mapAcademicYearToDto(AcademicYear academicYear);

    @Mappings({
            @Mapping(target = "terms", ignore = true),
            @Mapping(target = "studentApplications", ignore = true)
    })
    @InheritInverseConfiguration
    AcademicYear mapDtoToAcademicYear(AcademicYearDto academicyearDto);

}
