package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.AcademicYearDto;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.Term;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Set;

@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface AcademicYearMapper {

	@Mappings({
			@Mapping(target = "numberOfTerms", expression = "java(mapNumberOfTerms(academicyear.getTerms()))")
	})
	AcademicYearDto mapAcademicYearToDto(AcademicYear academicyear);

	default Integer mapNumberOfTerms(Set<Term> terms){
		return terms.size();
	}

	@Mappings({
			@Mapping(target = "terms", ignore = true),
			@Mapping(target = "studentApplications", ignore = true)
	})
	@InheritInverseConfiguration
	AcademicYear mapDtoToAcademicYear(AcademicYearDto academicyearDto);

}
