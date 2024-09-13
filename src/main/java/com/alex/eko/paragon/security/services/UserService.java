package com.alex.eko.paragon.security.services;


import com.alex.eparagon.emails.EmailServiceSender;
import com.alex.eparagon.errors.exceptions.ObjectAlreadyExistException;
import com.alex.eparagon.security.config.jwt.TokenService;
import com.alex.eparagon.security.domain.Token;
import com.alex.eparagon.security.domain.User;
import com.alex.eparagon.security.domain.enums.Role;
import com.alex.eparagon.security.dto.RegisterDto;
import com.alex.eparagon.security.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final String TAG = "USER_AUTHENTICATION_SERVICE - ";

    private final UserRepo userRepo;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    private final EmailServiceSender emailServiceSender;


    @SneakyThrows
    public void register(RegisterDto dto) {
        log.info(TAG + "Register user with role USER");

        if (userRepo.existsByEmail(dto.getEmail())) {
            throw new ObjectAlreadyExistException("User with email {} is already exists");
        }


        User user = new User();
        user.setFirstname(dto.getFirstName());
        user.setLastname(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(List.of(Role.USER));


        User savedUser = userRepo.save(user);
        Token token = tokenService.createRefreshToken(savedUser);
        emailServiceSender.sendConfirmationEmail(savedUser, token);
    }




}
