package com.alex.eko.paragon.security.jwt;

import com.alex.eko.paragon.security.domain.Role;
import com.alex.eko.paragon.security.domain.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.jsonwebtoken.Claims;
import lombok.*;

import java.security.Principal;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomPrincipal implements Principal {
    private Long id;
    private String name;  // email

    private List<Role> roles;

    @Override
    public String getName() {
        return name;
    }


    public CustomPrincipal(Claims claims) {
        this.id = ((Integer) claims.get("id")).longValue();
        this.name = (String) claims.get("email");
        this.roles = (List<Role>) claims.get("roles");
    }

    public CustomPrincipal(User user) {
        this.id = user.getId();
        this.name = user.getEmail();
        this.roles = user.getRoles();
    }
}
