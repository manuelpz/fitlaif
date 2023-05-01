package com.gym.fitlaif.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.gym.fitlaif.exceptions.FitLaifConflictException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FitLaifConflictException.class)
    public ResponseEntity<Object> handleEntrenamientoConflictException(
            FitLaifConflictException ex, WebRequest request) {

        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.CONFLICT.value());
        error.put("error", HttpStatus.CONFLICT.getReasonPhrase());
        error.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}





