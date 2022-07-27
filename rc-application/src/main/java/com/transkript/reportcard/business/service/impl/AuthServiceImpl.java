package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.request.UserRequest;
import com.transkript.reportcard.api.dto.response.UserResponse;
import com.transkript.reportcard.business.service.i.AuthService;
import com.transkript.reportcard.data.entity.User;
import com.transkript.reportcard.data.enums.Role;
import com.transkript.reportcard.data.repository.UserRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final @NotNull UserRepository userRepository;

    @NotNull
    @Override
    public UserResponse.Register registerUser(@NotNull UserRequest.Register userRequest) {
        User user = User.builder().username(userRequest.username()).roles(List.of(Role.ROLE_USER))
                .firstName(userRequest.firstName()).lastName(userRequest.lastName())
                .password(bCryptPasswordEncoder.encode(userRequest.password())).build();
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new EntityException.AlreadyExists("user", user.getUsername());
        }
        System.out.println(user);
        user = userRepository.save(user);
        return new UserResponse.Register(user.getId(), user.getUsername(), String.format("User '%s' registered successfully", user.getUsername()));
    }

    @NotNull
    @Override
    public UserResponse.Login loginUser(@NotNull UserRequest.Login userRequest) {
        System.out.println(userRequest);
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                userRequest.username(), userRequest.password()
        ));
        JwtToken jwtToken = jwtUtil.generateToken(userRequest.username());
        return new UserResponse.Login(jwtToken.getValue(), "Login successful");
    }
}
