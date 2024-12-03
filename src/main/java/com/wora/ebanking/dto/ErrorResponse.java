package com.wora.ebanking.dto;

import java.time.LocalDateTime;

public record ErrorResponse(int code, LocalDateTime timestamp, String message, String description, Object errors) {
    public ErrorResponse(int code, String message, String description, Object errors) {
        this(code, LocalDateTime.now(), message, description, errors);
    }
}

