package com.example.api.exception;

public class ProductNameAlreadyExistsException extends RuntimeException {

    public ProductNameAlreadyExistsException(String message) {
        super(message);
    }
}