package com.alex.eko.paragon.utils.exceptions;


import com.alex.eko.paragon.utils.exceptions.errors.ResourceNotFoundException;
import com.alex.eko.paragon.utils.exceptions.errors.UserFailedAuthentication;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;


@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleResourceNotFound(ResourceNotFoundException ex) {
        return new ExceptionBody(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler({
            IllegalArgumentException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleIllegalArgument(Exception ex) {
        return new ExceptionBody(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }


    @ExceptionHandler({
            IllegalStateException.class,
            IOException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionBody handleUserAlreadyExistException(Exception ex) {
        return new ExceptionBody(HttpStatus.CONFLICT.value(), ex.getMessage());
    }


    @ExceptionHandler({
            UserFailedAuthentication.class,
            ExpiredJwtException.class,
            UnsupportedJwtException.class,
            MalformedJwtException.class,
            SignatureException.class,
            NonceExpiredException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionBody handleJwtExceptions(Exception ex) {
        return new ExceptionBody(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

}