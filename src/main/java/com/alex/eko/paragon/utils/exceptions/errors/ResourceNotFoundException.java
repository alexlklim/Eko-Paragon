package com.alex.eko.paragon.utils.exceptions.errors;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException( final String message) {
        super(message);
    }
}
