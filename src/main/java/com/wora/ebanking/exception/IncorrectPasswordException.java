package com.wora.ebanking.exception;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
