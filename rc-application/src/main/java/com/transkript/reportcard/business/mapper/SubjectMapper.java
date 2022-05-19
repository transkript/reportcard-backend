package com.transkript.reportcard.business.mapper;


import com.transkript.reportcard.api.dto.SubjectDto;
import com.transkript.reportcard.data.entity.Subject;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface SubjectMapper {

    @Mappings({@Mapping(target = "sectionId", expression = "java(subject.getSection().getId())")})
    SubjectDto mapSubjectToDto(Subject subject);

    @Mappings({
            @Mapping(target = "section", ignore = true),
    })
    @InheritInverseConfiguration
    Subject mapDtoToSubject(SubjectDto subjectDto);

}
