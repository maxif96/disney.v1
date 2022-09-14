package com.api.disney.exception;

public class NameAlreadyExists extends Exception{

    public NameAlreadyExists(String errorMessage) {
        super(errorMessage);
    }
}
