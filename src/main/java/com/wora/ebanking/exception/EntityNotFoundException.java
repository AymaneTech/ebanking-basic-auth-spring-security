package com.wora.ebanking.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public static EntityNotFoundException byUsername(String username) {
        return new EntityNotFoundException(String.format("User with username: %s not found", username));
    }
}
