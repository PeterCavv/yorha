package com.dataproject.yorha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
//public class ExceptionGlobalHandler {
//
//    @ExceptionHandler(ObjectNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<ErrorResponse> handleObjectNotFound(ObjectNotFoundException ex){
//
//    }
