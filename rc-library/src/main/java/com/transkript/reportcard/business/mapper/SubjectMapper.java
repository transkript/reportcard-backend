package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.SubjectDto;
import com.transkript.reportcard.data.entity.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", implementationPackage="<PACKAGE_NAME>.impl")
public interface SubjectMapper {

	SubjectDto mapSubjectToDto(Subject subject);

	Subject mapDtoToSubject(SubjectDto subjectDto);

}
