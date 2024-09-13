package com.alex.eko.paragon.security.utils;

import com.alex.eparagon.security.config.jwt.CustomPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecHolder {

    public static CustomPrincipal getPrincipal() {
        return ((CustomPrincipal)
                (SecurityContextHolder.getContext().getAuthentication())
                        .getPrincipal());
    }
}
