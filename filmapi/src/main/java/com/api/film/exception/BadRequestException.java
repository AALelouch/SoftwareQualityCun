package com.api.film.exception;

/**
 * @see com.api.film.exception.advice.BadRequestExceptionAdvice
 * Description: Exception for all bad request, it has a message for more flexibility
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
