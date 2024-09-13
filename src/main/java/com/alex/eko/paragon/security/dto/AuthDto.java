package com.alex.eko.paragon.security.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema(description = "Auth DTO")
public class AuthDto {

    @Schema(description = "First name", example = "Alex")
    String firstName;

    @Schema(description = "Last name", example = "Klim")
    String lastName;

    List<String> role = new ArrayList<>();

    @Schema(description = "Access token", example = "eyJhbJ9.eyJzdWyfQ.Sflsw5c")
    String accessToken;

    @Schema(description = "Refresh token", example = "d133f77b-21c5-4ecf-8576-9b79e7d46be7")
    UUID refreshToken;
}
