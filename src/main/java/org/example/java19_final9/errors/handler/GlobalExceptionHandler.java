package org.example.java19_final9.errors.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.security.access.AccessDeniedException;

import java.util.NoSuchElementException;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public String notFoundHandler(Model model, HttpServletRequest request) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
        model.addAttribute("details", request.getRequestURI());
        return "errors/error";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String accessDeniedHandler(Model model, HttpServletRequest request) {
        model.addAttribute("status", HttpStatus.FORBIDDEN.value());
        model.addAttribute("reason", HttpStatus.FORBIDDEN.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/accessDenied";
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public String internalServerError(Model model, HttpServletRequest request) {
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("reason", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/error";
    }



    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String methodNotSupportedHandler(HttpRequestMethodNotSupportedException ex, Model model, HttpServletRequest request) {
        model.addAttribute("status", HttpStatus.METHOD_NOT_ALLOWED.value());
        model.addAttribute("reason", HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public String mediaTypeNotSupportedHandler(HttpMediaTypeNotSupportedException ex, Model model, HttpServletRequest request) {
        model.addAttribute("status", HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        model.addAttribute("reason", HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String mediaTypeNotAcceptableHandler(HttpMediaTypeNotAcceptableException ex, Model model, HttpServletRequest request) {
        model.addAttribute("status", HttpStatus.NOT_ACCEPTABLE.value());
        model.addAttribute("reason", HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String noHandlerFoundExceptionHandler(NoHandlerFoundException ex, Model model, HttpServletRequest request) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/error";
    }


}
