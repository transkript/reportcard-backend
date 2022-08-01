package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.UserDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import org.jetbrains.annotations.NotNull;

public interface UserService {
    @NotNull
    EntityResponse save(@NotNull UserDto userDto);
}
