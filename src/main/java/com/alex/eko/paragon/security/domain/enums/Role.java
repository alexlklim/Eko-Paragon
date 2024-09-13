package com.alex.eko.paragon.security.domain.enums;

import lombok.Getter;


@Getter
public enum Role {
   ADMIN, MAN, USER;

    public String getName() {
        return this.name();
    }
}


