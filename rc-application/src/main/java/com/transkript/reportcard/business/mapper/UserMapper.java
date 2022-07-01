package com.transkript.reportcard.business.mapper;

import com.transkript.reportcard.api.dto.UserDto;
import com.transkript.reportcard.data.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    User schoolAdminDtoToSchoolAdmin(UserDto userDto);

    UserDto schoolAdminToSchoolAdminDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateSchoolAdminFromSchoolAdminDto(UserDto userDto, @MappingTarget User user);
}
