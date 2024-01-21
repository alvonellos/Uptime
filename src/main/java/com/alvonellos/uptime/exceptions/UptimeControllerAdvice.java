package com.alvonellos.uptime.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;
import java.util.Optional;

@ControllerAdvice(annotations = RestController.class)
@RequestMapping(produces = "application/api.error+json")
@Log
public class UptimeControllerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<UptimeAPIError> entityNotFound(final EntityNotFoundException e) {
        log.info("EntityNotFoundException: " + e.getMessage());
        return error(e, HttpStatus.NOT_FOUND, e.getMessage().toString());
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<UptimeAPIError> noSuchElement(final NoSuchElementException e) {
        log.info("NoSuchElementException: " + e.getMessage());
        return error(e, HttpStatus.NOT_FOUND, e.getMessage().toString());
    }
    @ExceptionHandler(UptimeIdNotFoundException.class)
    public ResponseEntity<UptimeAPIError> notFoundException(final UptimeIdNotFoundException e) {
        log.info("EntityNotFoundException: " + e.getMessage() + " " +  e.getId());
        return error(e, HttpStatus.NOT_FOUND, e.getId().toString());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<UptimeAPIError> argumentNotValid(final UptimeIllegalArgumentException e) {
        log.info("IllegalArgumentException handler executed");
        return error(e, HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UptimeAPIError> internalServerError(final Exception e) {
        log.severe("Internal server error: " + e.getMessage() + " " + e.getStackTrace());
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private ResponseEntity<UptimeAPIError> error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new UptimeAPIError(logRef, message, exception), httpStatus);
    }

}