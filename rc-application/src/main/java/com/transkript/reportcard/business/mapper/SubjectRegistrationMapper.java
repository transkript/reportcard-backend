package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.SubjectRegistrationDto;
import com.transkript.reportcard.data.entity.SubjectRegistration;
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
    @Mapping(source = "subjectId", target = "subject.id")
    @Mapping(source = "satId", target = "studentApplicationTrial.id")
    SubjectRegistration subjectRegistrationDtoToSubjectRegistration(SubjectRegistrationDto subjectRegistrationDto);

    @InheritInverseConfiguration(name = "subjectRegistrationDtoToSubjectRegistration")
    SubjectRegistrationDto subjectRegistrationToSubjectRegistrationDto(SubjectRegistration subjectRegistration);

    @InheritConfiguration(name = "subjectRegistrationDtoToSubjectRegistration")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SubjectRegistration updateSubjectRegistrationFromSubjectRegistrationDto(SubjectRegistrationDto subjectRegistrationDto, @MappingTarget SubjectRegistration subjectRegistration);
}
