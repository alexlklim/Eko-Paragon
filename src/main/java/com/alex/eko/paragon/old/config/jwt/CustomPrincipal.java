package com.alex.eko.paragon.old.config.jwt;

import com.alex.eko.paragon.old.security.domain.User;
import com.alex.eko.paragon.old.security.services.GrantedAuthorityProvider;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
    private String name; // email
    private List<String> roles;

    @Override
    public String getName() {
        return name;
    }

    public CustomPrincipal(User user) {
        this.id = user.getId();
        this.name = user.getEmail();
        this.roles = GrantedAuthorityProvider.convertAuthoritiesToStringList(user.getAuthorities());
    }
}
