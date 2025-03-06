package com.api.film.exception.advice;

import com.api.film.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @see NotFoundExceptionAdvice
 * Description: Advice for BadRequestException, it has a response body, exception hanlder and the
 * 404 response code
 */
@ControllerAdvice
public class NotFoundExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundHandler(NotFoundException notFoundException) {
        return notFoundException.getMessage();
    }
}
