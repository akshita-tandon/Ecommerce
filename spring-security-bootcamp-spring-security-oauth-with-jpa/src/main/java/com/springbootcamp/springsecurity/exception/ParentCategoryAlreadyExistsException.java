package com.springbootcamp.springsecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ParentCategoryAlreadyExistsException extends RuntimeException {
    public ParentCategoryAlreadyExistsException(String s) {
        super((s));
    }
}
