package com.inditex.price.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for managing API exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation exceptions when the input parameters are invalid.
     *
     * @param ex the MethodArgumentNotValidException
     * @return a Mono containing the ApiError response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    String fieldName = ((FieldError) error).getField();
                    return String.format("%s: %s", fieldName, error.getDefaultMessage());
                })
                .collect(Collectors.toList());
        ApiError apiError = new ApiError("Validation error", errors);
        return Mono.just(apiError);
    }

    /**
     * Handles PriceNotFoundException when a price is not found for given parameters.
     *
     * @param ex the PriceNotFoundException
     * @return a Mono containing the ApiError response
     */
    @ExceptionHandler(PriceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ApiError> handlePriceNotFoundException(PriceNotFoundException ex) {
        ApiError apiError = new ApiError("Price Not Found", ex.getMessage());
        return Mono.just(apiError);
    }
    
    /**
     * Handles cases where the request body is not readable (e.g., null body).
     *
     * @param ex the HttpMessageNotReadableException
     * @return a Mono containing the ApiError response
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ApiError> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        ApiError apiError = new ApiError("Bad Request", "Request body must not be null or malformed");
        return Mono.just(apiError);
    }
}
