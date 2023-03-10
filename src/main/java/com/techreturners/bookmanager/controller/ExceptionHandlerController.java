package com.techreturners.bookmanager.controller;

import com.techreturners.bookmanager.exception.DuplicateResourceException;
import com.techreturners.bookmanager.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.techreturners.bookmanager.exception.ErrorType;


@RestControllerAdvice(annotations = RestController.class)
public class ExceptionHandlerController {

    @ExceptionHandler({DuplicateResourceException.class})
    public ResponseEntity<ErrorType> handleDuplicateResourceException(
            RuntimeException ex) {
        return new ResponseEntity<ErrorType>(
                new ErrorType(ex.getMessage(), "BOOK_ALREADY_EXIST", "DATA NOT FOUND FOR GIVEN ID", "Book"),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorType> handleResourceNotFoundException(
            RuntimeException ex) {
        return new ResponseEntity<ErrorType>(
                new ErrorType(ex.getMessage(), "NO_BOOK_FOUND", "DATA NOT FOUND FOR GIVEN ID", "Book"),
                HttpStatus.NOT_FOUND);
    }
}
