package com.alex.eko.paragon.security.repo;

import com.alex.eko.paragon.security.jwt.CustomPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public class SH {

    public static CustomPrincipal getPrincipal() {
        return ((CustomPrincipal)
                (SecurityContextHolder.getContext().getAuthentication())
                        .getPrincipal());
    }

    public static Long getUserId() {
        return ((CustomPrincipal)
                (SecurityContextHolder.getContext().getAuthentication())
                        .getPrincipal()).getId();
    }

}
