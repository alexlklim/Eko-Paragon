package com.alex.eko.paragon.security.jwt;

import com.alex.eko.paragon.utils.DateService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
public class JwtService {

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(CustomPrincipal principal) {
        return generateToken(new HashMap<>(), principal);
    }

    public String generateToken(Map<String, Object> extraClaims, CustomPrincipal principal) {
        extraClaims.put("userUUID", principal.getId());
        extraClaims.put("email", principal.getName());
        return buildToken(extraClaims, principal.getName());
    }


    private String buildToken(Map<String, Object> extraClaims, String subject) {
        return Jwts.builder().setClaims(extraClaims).setSubject(subject)
                .setIssuedAt(DateService.convertToDate(DateService.getDateNow()))
                .setExpiration(DateService.addFiveMinutesToDate(DateService.getDateNow()))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey())
                .build().parseClaimsJws(token).getBody();
    }

    @Value("${jwt.secret}")
    private String secretKey;

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}