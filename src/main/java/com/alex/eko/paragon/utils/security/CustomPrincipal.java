package com.alex.eko.paragon.utils.security;

import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Principal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomPrincipal implements Principal {
    private Long id;
    private String name;

    private Long companyId;
    private Long warehouseId;
    private List<String> roles;

    @Override
    public String getName() {
        return name;
    }


    public CustomPrincipal(Claims claims) {
        this.id = ((Integer) claims.get("id")).longValue();
        this.name = (String) claims.get("email");
        this.companyId = ((Integer) claims.get("cid")).longValue();
        this.warehouseId = ((Integer) claims.get("wid")).longValue();
        this.roles = (List<String>) claims.get("roles");
    }

}