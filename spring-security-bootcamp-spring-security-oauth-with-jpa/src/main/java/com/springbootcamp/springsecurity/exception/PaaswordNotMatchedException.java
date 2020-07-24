package com.springbootcamp.springsecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaaswordNotMatchedException extends RuntimeException {

    public PaaswordNotMatchedException(String message) {
        super(message);
    }
}
