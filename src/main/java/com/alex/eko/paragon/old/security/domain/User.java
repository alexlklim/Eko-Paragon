package com.alex.eko.paragon.old.security.domain;

import com.alex.eko.paragon.old.security.domain.enums.Role;
import com.alex.eko.paragon.old.security.services.GrantedAuthorityProvider;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @CreatedDate
    LocalDateTime created;

    @LastModifiedDate
    LocalDateTime updated;

    @Column(name = "is_enabled")
    boolean isEnabled;

    String firstname;
    String lastname;

    @Column(name = "email", unique = true)
    String email;


    @JsonIgnore
    @Column(name = "password")
    String password;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    List<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return GrantedAuthorityProvider.getAllAuthorities(roles).stream()
                .toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }


}