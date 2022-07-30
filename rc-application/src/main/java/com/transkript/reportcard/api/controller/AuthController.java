package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.api.dto.request.UserRequest;
import com.transkript.reportcard.api.dto.response.UserResponse;
import com.transkript.reportcard.business.service.i.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<UserResponse.Register> register(@RequestBody UserRequest.Register userRequest) {
        log.info("Registering user: {}", userRequest.username());
        return ResponseEntity.ok(authService.registerUser(userRequest));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserResponse.Auth> login(@RequestBody UserRequest.Login userRequest, HttpSession session) {

        log.info("Log in user: {}", userRequest.username());
        return ResponseEntity.ok(authService.loginUser(userRequest, session));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<UserResponse.Auth> logout(@RequestBody UserRequest.Logout logout, HttpSession session) {
        return ResponseEntity.ok(authService.logout(logout, session));
    }
}
