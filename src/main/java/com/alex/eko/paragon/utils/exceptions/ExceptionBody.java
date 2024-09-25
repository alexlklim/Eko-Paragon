package com.alex.eko.paragon.utils.exceptions;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionBody {
    @Schema(description = "Error code representing the type of error",
            example = "404")
    private Integer code;

    @Schema(description = "Description of the error",
            example = "Resource not found")
    private String message;

    public ExceptionBody(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}