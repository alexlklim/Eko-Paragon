package com.alex.eko.paragon.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@NoArgsConstructor
@Data
@Schema(description = "Name Dto")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DtoName {

    @Schema(description = "Name", example = "Something")
    String name;

}
