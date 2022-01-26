package com.engine.realestatesearchapp.utilities.exceptions;

public class ServerErrorException extends RuntimeException {
    public ServerErrorException(String e) {
        super(e);
    }
}