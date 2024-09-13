package com.alex.eko.paragon.security.config.jwt;


import com.alex.eparagon.emails.EmailServiceSender;
import com.alex.eparagon.errors.exceptions.UserFailedAuthentication;
import com.alex.eparagon.security.domain.Token;
import com.alex.eparagon.security.domain.User;
import com.alex.eparagon.security.domain.enums.Role;
import com.alex.eparagon.security.dto.AuthDto;
import com.alex.eparagon.security.dto.LoginDto;
import com.alex.eparagon.security.dto.TokenDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final String TAG = "AUTHENTICATION_SERVICE - ";

    private final UserAuthService userAuthService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final EmailServiceSender emailServiceSender;

    @Value("${jwt.secret}")
    public String SECRET_KEY;

    @SneakyThrows
    public AuthDto authenticate(LoginDto request) {
        log.info(TAG + "authenticate");
        User user = userAuthService.getUserByEmail(request.getEmail())
                .orElseThrow(() -> new UserFailedAuthentication("Authentication failed. User not found"));
        if (!user.isEnabled())
            throw new UserFailedAuthentication("Authentication failed. Account not endbled");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return getAuthDto(user);
    }

    private AuthDto getAuthDto(User user) {
        log.info(TAG + "getAuthDto");
        Token refreshToken = tokenService.createRefreshToken(user);
        AuthDto authDto = new AuthDto();
        authDto.setFirstName(user.getFirstname());
        authDto.setLastName(user.getLastname());
        authDto.setAccessToken(jwtService.generateToken(new CustomPrincipal(user)));

        System.out.println(
                 Jwts.parser()
                        .setSigningKey(getSignInKey())
                        .parseClaimsJws(authDto.getAccessToken())
                        .getBody()
        );
        authDto.setRefreshToken(refreshToken.getToken());
        authDto.setRole(
                user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toList())
        );
        return authDto;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @SneakyThrows
    public AuthDto refreshToken(TokenDto request) {
        log.info(TAG + "refreshToken");
        User user = userAuthService.getUserByEmail(request.getEmail())
                .orElseThrow(() -> new UserFailedAuthentication("Authentication failed"));
        if (!tokenService.checkIfTokenValid(request.getRefreshToken(), user))
            throw new UserFailedAuthentication("Authentication failed");
        tokenService.deleteTokenByUser(user);
        return getAuthDto(user);
    }

    @SneakyThrows
    public void forgotPasswordAction(String email) {
        log.info(TAG + "forgotPasswordAction");
        User user = userAuthService.getUserByEmail(email)
                .orElseThrow(() -> new UserFailedAuthentication("Authentication failed"));
        tokenService.deleteTokenByUser(user);
        Token token = tokenService.createRefreshToken(user);
        emailServiceSender.sendLinkToPasswordRecovery(user, token);
    }

    @SneakyThrows
    public void recoveryPassword(String token, String password) {
        log.info(TAG + "recoveryPassword");
        Token tokenFromDB = tokenService.getTokenByToken(UUID.fromString(token))
                .orElseThrow(() -> new UserFailedAuthentication("Authentication failed"));
        User user = userAuthService.getById(tokenFromDB.getUser().getId());
        tokenService.deleteTokenByUser(user);
        userAuthService.changePasswordForgot(user.getEmail(), password);
        emailServiceSender.sendNotificationEmailThatPasswordWasChanged(user.getEmail());
    }

    @Transactional
    public void logout(CustomPrincipal principal) {
        log.info(TAG + "logout");
        User user = userAuthService.getUserByEmail(principal.getName())
                .orElseThrow(() -> new UserFailedAuthentication("Authentication failed"));
        tokenService.deleteTokenByUser(user);
        SecurityContextHolder.clearContext();
    }
}
