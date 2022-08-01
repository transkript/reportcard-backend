package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.GradeDto;
import com.transkript.reportcard.data.entity.composite.SubjectRegistrationKey;
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
    @Mapping(source = "key.subjectId", target = "key.registrationKey.subjectId")
    @Mapping(source = "key.satId", target = "key.registrationKey.satId")
    @Mapping(source = "sequenceId", target = "sequence.id")
    @Mapping(source = "key", target = "key")
    Grade gradeDtoToGrade(GradeDto gradeDto);

    default SubjectRegistrationKey mapRegistrationKey(GradeDto gradeDto) {
        return new SubjectRegistrationKey(
                gradeDto.key().subjectId(),
                gradeDto.key().satId()
        );
    }

    @InheritInverseConfiguration(name = "gradeDtoToGrade")
    GradeDto gradeToGradeDto(Grade grade);

    @InheritConfiguration(name = "gradeDtoToGrade")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Grade updateGradeFromGradeDto(GradeDto gradeDto, @MappingTarget Grade grade);
}
