package com.alex.eko.paragon.security.domain;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    MAN("ROLE_MAN"),
    EMP("ROLE_EMP"),
    USER("ROLE_USER");

    private final List<String> permissions;

    Role(String... permissions) {
        this.permissions = Arrays.asList(permissions);
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(getPermissions()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }


    public static Role fromString(String roleName) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No enum constant " + roleName + " found in Role");
    }

}