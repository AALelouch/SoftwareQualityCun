package com.api.film.exception;

/**
 * @see com.api.film.exception.advice.NotFoundExceptionAdvice
 * Description: Exception for all not found, it has a message for more flexibility
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
