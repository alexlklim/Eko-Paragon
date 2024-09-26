package com.alex.eko.paragon.security.controller;


import com.alex.eko.paragon.security.UserService;
import com.alex.eko.paragon.security.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1/planner/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final String TAG = "ADMIN_CONTROLLER - ";

    private final UserService userService;


    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get user by id")
    @GetMapping("/{user_id}")
    public UserDto getUserById(
            @PathVariable("user_id") Long userId) {
        log.info(TAG + "getUserById");
        return userService.getUserDTOById(userId);
    }

    @Operation(summary = "Get info about all user by id (only for admin)")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserDto> getAllUsers() {
        log.info(TAG + "getAllUsers");
        return userService.getAllUsers();

    }

    @Operation(summary = "Update user (for ADMIN)")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{user_id}")
    public void updateUser(
            @PathVariable("user_id") Long userId,
            @RequestBody UserDto userDto) {
        log.info(TAG + "updateUser");
        userService.updateUserByAdmin(userId, userDto);
    }


}
