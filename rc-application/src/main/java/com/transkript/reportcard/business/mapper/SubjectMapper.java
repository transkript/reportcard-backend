package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.SubjectDto;
import com.transkript.reportcard.data.entity.Subject;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SubjectMapper {
    @Mapping(source = "sectionId", target = "section.id")
    Subject subjectDtoToSubject(SubjectDto subjectDto);

    @Mapping(source = "section.id", target = "sectionId")
    SubjectDto subjectToSubjectDto(Subject subject);

    @Mapping(source = "sectionId", target = "section.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Subject updateSubjectFromSubjectDto(SubjectDto subjectDto, @MappingTarget Subject subject);
}
