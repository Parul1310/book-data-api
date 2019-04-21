package com.bcs.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookResourceNotFoundException extends RuntimeException {
    public BookResourceNotFoundException(String message) {
        super(message);
    }
}
