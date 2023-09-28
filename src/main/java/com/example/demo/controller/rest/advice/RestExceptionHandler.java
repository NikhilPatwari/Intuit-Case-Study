package com.example.demo.controller.rest.advice;

import com.example.demo.dto.Status;
import com.example.demo.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Status> handleNotFoundException(NotFoundException ex) {
        var response = Status.getErrorStatus(Collections.singletonList(ex.getErrorMessage()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Status> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
        var response = Status.getErrorStatus(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
