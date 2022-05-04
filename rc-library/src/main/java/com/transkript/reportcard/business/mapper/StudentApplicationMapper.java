package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.data.entity.StudentApplication;
import org.mapstruct.Mapper;

// @Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface StudentApplicationMapper {

	StudentApplicationDto mapStudentApplicationToDto(StudentApplication studentapplication);

	StudentApplication mapDtoToStudentApplication(StudentApplicationDto studentapplicationDto);

}
