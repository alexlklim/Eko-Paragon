package com.alex.eko.paragon.utils.exceptions.errors;

public class UserFailedAuthentication extends RuntimeException{

    public UserFailedAuthentication( final String message) {
        super(message);
    }

}
