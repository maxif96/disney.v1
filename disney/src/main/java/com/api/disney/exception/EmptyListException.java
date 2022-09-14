package com.api.disney.exception;

public class EmptyListException extends Exception{

    public EmptyListException(String errorMessage) {
        super(errorMessage);
    }
}
