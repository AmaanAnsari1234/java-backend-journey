package com.example.employee_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleEmployeeNotFoundException(EmployeeNotFoundException ex)
    {
        Map<String, Object> body = Map.of(
                "status", 404,
                "message", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}