package com.sg.ep.controller;

import com.sg.ep.exception.EmployeeException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

/**
 * The type Employee controller advice.
 */
@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class EmployeeControllerAdvice extends ResponseEntityExceptionHandler {

    /**
     * Not found exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<VndErrors> notFoundException(final EmployeeException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    /**
     * Assertion exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<VndErrors> assertionException(final IllegalArgumentException e) {
        return error(e, HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    }

    private ResponseEntity<VndErrors> error(
            final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message =
                Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
    }
}