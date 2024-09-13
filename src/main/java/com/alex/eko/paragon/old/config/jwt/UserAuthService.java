package com.alex.eko.paragon.old.config.jwt;


import com.alex.eko.paragon.old.emails.EmailServiceSender;
import com.alex.eko.paragon.old.errors.exceptions.ResourceNotFoundException;
import com.alex.eko.paragon.old.errors.exceptions.UserNotRegisterYet;
import com.alex.eko.paragon.old.security.domain.Token;
import com.alex.eko.paragon.old.security.domain.User;
import com.alex.eko.paragon.old.security.dto.PasswordDto;
import com.alex.eko.paragon.old.security.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserAuthService {
    private final String TAG = "USER_AUTHENTICATION_SERVICE - ";

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final TokenService tokenService;

    private final EmailServiceSender emailServiceSender;


    @SneakyThrows
    public void changePassword(PasswordDto dto, CustomPrincipal principal) {
        log.info(TAG + "changePassword");
        User user = userRepo.getUser(principal.getId());
        if (!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword()))
            throw new AuthenticationException("Password is wrong");
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepo.save(user);
        emailServiceSender.sendNotificationEmailThatPasswordWasChanged(user.getEmail());
    }


    @SneakyThrows
    public void changePasswordForgot(String email, String password) {
        log.info(TAG + "changePasswordForgot");
        User user = userRepo.getUserByEmail(email).orElseThrow(
                () -> new UserNotRegisterYet("User with email " + email + " is not registered"));
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);
        emailServiceSender.sendNotificationEmailThatPasswordWasChanged(email);
    }


    public User getById(Long userId) {
        log.info(TAG + "getById");
        return userRepo.findById(userId).orElse(null);
    }


    @SneakyThrows
    public void activate(String tokenString) {
        log.info(TAG + "activate");
        Token token = tokenService.getTokenByToken(UUID.fromString(tokenString))
                .orElseThrow(
                        () -> new ResourceNotFoundException("Token not found")
                );
        User user = token.getUser();
        user.setEnabled(true);
        tokenService.deleteTokenByUser(user);
        userRepo.save(user);
    }


    public Optional<User> getUserByEmail(String email) {
        return userRepo.getUserByEmail(email);
    }

}