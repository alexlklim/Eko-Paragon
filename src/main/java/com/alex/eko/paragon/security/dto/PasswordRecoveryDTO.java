package com.alex.eko.paragon.security.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Password recovery DTO")
public class PasswordRecoveryDTO {

    @Schema(description = "password", example = "1122")
    private String password;


}
