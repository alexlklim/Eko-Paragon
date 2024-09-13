package com.alex.eko.paragon.old.security.utils;

import com.alex.eko.paragon.old.config.jwt.CustomPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecHolder {

    public static CustomPrincipal getPrincipal() {
        return ((CustomPrincipal)
                (SecurityContextHolder.getContext().getAuthentication())
                        .getPrincipal());
    }
}
