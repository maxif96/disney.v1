package com.api.disney.exception;

public class EmailAlreadyExists extends Exception{

    public EmailAlreadyExists(String errorMessage) {
        super(errorMessage);
    }
}
