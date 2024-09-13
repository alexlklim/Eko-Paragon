package com.alex.eko.paragon.security.services;

import com.alex.eparagon.security.domain.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GrantedAuthorityProvider {
    public static List<SimpleGrantedAuthority> getAllAuthorities(List<Role> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (roles != null) {
            authorities.addAll(roles.stream()
                    .map(role -> new SimpleGrantedAuthority(role.name()))
                    .toList());
        }
        return authorities;
    }

    public static List<String> convertAuthoritiesToStringList(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
