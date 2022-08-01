package com.transkript.reportcard.business.service.i;

import com.transkript.reportcard.api.dto.request.UserRequest;
import com.transkript.reportcard.api.dto.response.UserResponse;
import javax.servlet.http.HttpSession;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface AuthService {
    @NotNull
    UserResponse.Register registerUser(@NotNull UserRequest.Register var1);

    @NotNull
    UserResponse.Auth loginUser(@NotNull UserRequest.Login var1, @NotNull HttpSession var2);

    @NotNull
    UserResponse.Auth logout(@NotNull UserRequest.Logout var1, @NotNull HttpSession var2);
}
