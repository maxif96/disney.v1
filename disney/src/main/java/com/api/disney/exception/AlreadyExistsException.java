package com.api.disney.exception;

public class AlreadyExistsException extends Exception{

    public AlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
