package com.engine.realestatesearchapp.utilities.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String e) {
        super(e);
    }
}