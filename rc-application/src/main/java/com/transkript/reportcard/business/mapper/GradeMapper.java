package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.GradeDto;
import com.transkript.reportcard.data.entity.relation.Grade;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface GradeMapper {
    @Mapping(source = "sequenceId", target = "sequence.id")
    @Mapping(source = "registrationId", target = "registration.id")
    Grade gradeDtoToGrade(GradeDto gradeDto);

    @InheritInverseConfiguration(name = "gradeDtoToGrade")
    GradeDto gradeToGradeDto(Grade grade);

    @InheritConfiguration(name = "gradeDtoToGrade")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Grade updateGradeFromGradeDto(GradeDto gradeDto, @MappingTarget Grade grade);
}
