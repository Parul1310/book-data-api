package com.bcs.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookException extends RuntimeException {
    public BookException(String message) {
        super(message);
    }
}
