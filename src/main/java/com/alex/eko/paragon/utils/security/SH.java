package com.alex.eko.paragon.utils.security;

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


    public static Long getCompanyId() {
        return ((CustomPrincipal)
                (SecurityContextHolder.getContext().getAuthentication())
                        .getPrincipal()).getCompanyId();
    }
}
