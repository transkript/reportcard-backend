package com.transkript.reportcard.api.controller;

import com.transkript.reportcard.business.service.i.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/user")
public class UserController {
    private final UserService userService;


}
