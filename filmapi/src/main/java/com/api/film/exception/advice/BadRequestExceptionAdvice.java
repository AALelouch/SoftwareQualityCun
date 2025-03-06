package com.api.film.exception.advice;

import com.api.film.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @see BadRequestException
 * Description: Advice for BadRequestException, it has a response body, exception hanlder and the
 * 400 response code
 */
@ControllerAdvice
public class BadRequestExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String badRequestHandler(BadRequestException badRequestException) {
        return badRequestException.getMessage();
    }
}
