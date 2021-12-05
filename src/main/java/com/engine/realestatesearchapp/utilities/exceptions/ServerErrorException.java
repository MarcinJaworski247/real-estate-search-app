package com.engine.realestatesearchapp.utilities.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ServerErrorException extends RuntimeException {
    public ServerErrorException(String e) {
        super(e);
    }
}