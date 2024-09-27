package com.inditex.price.application.exception;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class representing an API error response.
 */
@Data
public class ApiError {
    private LocalDateTime timestamp;
    private String message;
    private List<String> errors;

    /**
     * Constructor for ApiError with a list of error messages.
     *
     * @param message the general error message
     * @param errors  the list of specific error messages
     */
    public ApiError(String message, List<String> errors) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.errors = errors;
    }

    /**
     * Constructor for ApiError with a single error message.
     *
     * @param message the general error message
     * @param error   the specific error message
     */
    public ApiError(String message, String error) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.errors = List.of(error);
    }
}
