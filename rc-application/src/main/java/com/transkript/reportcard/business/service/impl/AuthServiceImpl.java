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
