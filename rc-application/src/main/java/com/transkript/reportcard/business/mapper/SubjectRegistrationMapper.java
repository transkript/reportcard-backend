package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.data.entity.composite.SubjectRegistrationKey;
import com.transkript.reportcard.data.entity.relation.SubjectRegistration;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SubjectRegistrationMapper {
    @Mapping(target = "key", expression = "java(mapKey(subjectRegistrationDto.key()))")
    @Mapping(source = "subjectId", target = "subject.id")
    @Mapping(source = "satId", target = "studentApplicationTrial.id")
    SubjectRegistration subjectRegistrationDtoToSubjectRegistration(SubjectRegistrationDto subjectRegistrationDto);

    default SubjectRegistrationKey mapKey(SubjectRegistrationDto.SubjectRegistrationKeyDto keyDto) {
        return new SubjectRegistrationKey(keyDto.subjectId(), keyDto.satId());
    }

    @InheritInverseConfiguration(name = "subjectRegistrationDtoToSubjectRegistration")
    SubjectRegistrationDto subjectRegistrationToSubjectRegistrationDto(SubjectRegistration subjectRegistration);

    @InheritConfiguration(name = "subjectRegistrationDtoToSubjectRegistration")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SubjectRegistration updateSubjectRegistrationFromSubjectRegistrationDto(SubjectRegistrationDto subjectRegistrationDto, @MappingTarget SubjectRegistration subjectRegistration);

    @AfterMapping
    default void linkGrades(@MappingTarget SubjectRegistration subjectRegistration) {
        subjectRegistration.getGrades().forEach(grade -> grade.setSubjectRegistration(subjectRegistration));
    }
}
