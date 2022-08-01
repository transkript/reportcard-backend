package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.StudentApplicationDto;
import com.transkript.reportcard.data.entity.relation.StudentApplication;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StudentApplicationMapper {
    StudentApplication studentApplicationDtoToStudentApplication(StudentApplicationDto studentApplicationDto);

    StudentApplicationDto studentApplicationToStudentApplicationDto(StudentApplication studentApplication);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StudentApplication updateStudentApplicationFromStudentApplicationDto(StudentApplicationDto studentApplicationDto, @MappingTarget StudentApplication studentApplication);
}
