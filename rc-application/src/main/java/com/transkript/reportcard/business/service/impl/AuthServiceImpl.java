package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.request.UserRequest;
import com.transkript.reportcard.api.dto.response.UserResponse;
import com.transkript.reportcard.business.service.i.AuthService;
import com.transkript.reportcard.data.entity.User;
import com.transkript.reportcard.data.enums.Role;
import com.transkript.reportcard.data.repository.UserRepository;
import com.transkript.reportcard.exception.EntityException;
import com.transkript.reportcard.exception.RcAuthenticationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authManager;
    private final @NotNull UserRepository userRepository;

    @NotNull
    @Override
    public UserResponse.Register registerUser(@NotNull UserRequest.Register userRequest) {
        User user = User.builder().username(userRequest.username()).roles(List.of(Role.ROLE_USER))
                .firstName(userRequest.firstName()).lastName(userRequest.lastName())
                .password(bCryptPasswordEncoder.encode(userRequest.password())).build();
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            log.error("User with username '{}' already exists", user.getUsername());
            throw new EntityException.AlreadyExists("user", user.getUsername());
        }
        user = userRepository.save(user);
        log.info("User with username '{}' added", user.getUsername());
        return new UserResponse.Register(user.getId(), user.getUsername(), String.format("User '%s' registered successfully", user.getUsername()));
    }

    @NotNull
    @Override
    public UserResponse.Login loginUser(@NotNull UserRequest.Login userRequest, @NotNull HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // check if user is already logged in
        if (authentication != null && authentication.getPrincipal() != null && !authentication.getPrincipal().equals("anonymousUser")) {
            System.out.println(authentication.getPrincipal());
            throw new RcAuthenticationException(String.format("User with username %s already logged in", userRequest.username()), HttpStatus.CONFLICT);
        }
        authentication = new UsernamePasswordAuthenticationToken(userRequest.username(), userRequest.password());
        try {
            authManager.authenticate(authentication);
        } catch (AuthenticationException e) {
            throw new RcAuthenticationException(e.getMessage());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext()
        );
        return new UserResponse.Login(session.getId(), "Login successful");
    }

    @NotNull
    @Override
    public String logout(@NotNull HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            log.info("Logging out user {}", authentication.getDetails());
            session.invalidate();
            return "Logged out user";
        } else {
            throw new RcAuthenticationException("No user logged in");
        }
    }
}
