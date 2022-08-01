package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.UserDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.UserMapper;
import com.transkript.reportcard.business.service.i.UserService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.config.constants.ResponseMessage;
import com.transkript.reportcard.data.entity.User;
import com.transkript.reportcard.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final @NotNull UserRepository userRepository;
    private final @NotNull UserMapper userMapper;
    private final String entityName = EntityName.USER;


    @NotNull
    @Override
    public EntityResponse save(@NotNull UserDto userDto) {
        User user = userMapper.schoolAdminDtoToSchoolAdmin(userDto);
        user = userRepository.save(user);
        // TODO complete this
        return new EntityResponse(user.getId(), ResponseMessage.SUCCESS.created(entityName), true);
    }
}
