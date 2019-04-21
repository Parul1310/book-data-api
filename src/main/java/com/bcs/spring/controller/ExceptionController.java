package com.bcs.spring.controller;


import com.bcs.spring.bean.BookEntity;
import com.bcs.spring.exception.BookException;
import com.bcs.spring.exception.BookResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    Logger LOG = LoggerFactory.getLogger(ExceptionController.class);


    @ExceptionHandler({BookResourceNotFoundException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    Map<String, String> resourceNotFound(Exception e) {
        Map<String, String> exception = new HashMap<>();

        LOG.error("Resource not Found: " + e.getMessage(), e);
        exception.put("code", "404");
        exception.put("reason", e.getMessage());

        return exception;
    }

    @ExceptionHandler({BookException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    Map<String, String> genericBookException(Exception e) {
        Map<String, String> exception = new HashMap<>();

        LOG.error( e.getMessage(), e);
        exception.put("reason", e.getMessage());

        return exception;
    }
}
