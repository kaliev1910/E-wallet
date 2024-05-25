package org.example.java19_final9.errors.handler;//package com.example.demo.errors.handler;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.dao.DataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.ErrorResponse;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.NoSuchElementException;
//
//@RestControllerAdvice
//@Slf4j
//public class GlobalExceptionHandler {
//    @ExceptionHandler(NoSuchElementException.class)
//    private ErrorResponse noSuchElementHandler(NoSuchElementException exception) {
//        log.error("Exception message: {}", exception.getMessage());
//        return ErrorResponse.builder(exception, HttpStatus.NOT_FOUND, exception.getMessage()).build();
//    }
//
//    @ExceptionHandler(DataAccessException.class)
//    private ErrorResponse handleDatabaseException(DataAccessException exception) {
//        log.error("Database Exception: ", exception);
//        return ErrorResponse.builder(exception, HttpStatus.INTERNAL_SERVER_ERROR, "Database error occurred").build();
//    }
//
//    @ExceptionHandler(Exception.class)
//    private ErrorResponse handleGeneralException(Exception exception) {
//        log.error("Unhandled Exception: ", exception);
//        return ErrorResponse.builder(exception, HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred").build();
//    }
//
//}
