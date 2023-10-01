package com.example.demo.controller.rest.advice;

import com.example.demo.dto.Status;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.ServiceUnavailableException;
import com.example.demo.exception.ValidationFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Status> handleNotFoundException(NotFoundException ex) {
        Status response = Status.getErrorStatus(Collections.singletonList(ex.getErrorMessage()));
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<Status> handleNotFoundException(AlreadyExistsException ex) {
        Status response = Status.getErrorStatus(Collections.singletonList(ex.getErrorMessage()));
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(value = ServiceUnavailableException.class)
    public ResponseEntity<Status> handleServiceUnavailableException(ServiceUnavailableException ex) {
        Status response = Status.getErrorStatus(Collections.singletonList(ex.getErrorMessage()));
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(value = ValidationFailureException.class)
    public ResponseEntity<Status> handleValidationFailureException(ValidationFailureException ex) {
        Status response = Status.getErrorStatus(Collections.singletonList(ex.getErrorMessage()));
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Status> notValid(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
        Status response = Status.getErrorStatus(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
