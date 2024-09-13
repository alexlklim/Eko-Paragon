package com.alex.eko.paragon.security.controller;

import com.alex.eparagon.security.dto.RegisterDto;
import com.alex.eparagon.security.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1/security/user")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "User API")
public class UserController {
    private final String TAG = "USER_CONTROLLER - ";

    private final UserService userService;


    @Operation(summary = "Register new user with ROLE_USER (not secured)")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void register(
            @RequestBody RegisterDto registerDto) {
        log.info(TAG + "register");
        userService.register(registerDto);
    }



}
