package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.StudentApplicationTrialDto;
import com.transkript.reportcard.data.entity.relation.StudentApplicationTrial;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface StudentApplicationTrialMapper {
    @Mapping(source = "academicYearId", target = "academicYear.id")
    @Mapping(target = "studentApplication", ignore = true)
    @Mapping(target = "subjectRegistrations", ignore = true)
    StudentApplicationTrial studentApplicationTrialDtoToStudentApplicationTrial(StudentApplicationTrialDto studentApplicationTrialDto);

    @Mapping(source = "academicYear.id", target = "academicYearId")
    @Mapping(source = "studentApplication.key", target = "applicationKeyDto")
    @Mapping(target = "numberOfSubjects", expression = "java(studentApplicationTrial.getSubjectRegistrations().size())")
    StudentApplicationTrialDto studentApplicationTrialToStudentApplicationTrialDto(StudentApplicationTrial studentApplicationTrial);

    @Mapping(source = "academicYearId", target = "academicYear.id")
    @Mapping(target = "studentApplication", ignore = true)
    @Mapping(target = "subjectRegistrations", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StudentApplicationTrial updateStudentApplicationTrialFromStudentApplicationTrialDto(StudentApplicationTrialDto studentApplicationTrialDto, @MappingTarget StudentApplicationTrial studentApplicationTrial);
}
