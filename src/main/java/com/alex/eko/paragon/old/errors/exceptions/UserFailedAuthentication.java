package com.alex.eko.paragon.old.errors.exceptions;

public class UserFailedAuthentication extends RuntimeException{

    public UserFailedAuthentication( final String message) {
        super(message);
    }

}
