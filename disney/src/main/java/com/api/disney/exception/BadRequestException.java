package com.api.disney.exception;

public class BadRequestException extends Exception{

    public BadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
