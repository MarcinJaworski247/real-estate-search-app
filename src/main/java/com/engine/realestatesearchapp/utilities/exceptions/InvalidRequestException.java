package com.engine.realestatesearchapp.utilities.exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String e) {
        super(e);
    }
}