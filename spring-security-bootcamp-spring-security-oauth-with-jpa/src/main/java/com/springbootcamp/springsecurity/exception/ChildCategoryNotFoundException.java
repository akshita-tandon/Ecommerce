package com.springbootcamp.springsecurity.exception;

public class ChildCategoryNotFoundException extends RuntimeException {
    public ChildCategoryNotFoundException(String message) {
        super(message);
    }
}
