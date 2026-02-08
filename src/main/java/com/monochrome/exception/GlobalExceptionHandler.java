package com.monochrome.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
        MethodArgumentNotValidException ex
    ) {
        Map<String, Object> fieldErrors = new HashMap<>();

        ex
            .getBindingResult()
            .getFieldErrors()
            .forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
            );

        Map<String, Object> response = new HashMap<>();
        response.put("error", "VALIDATION_ERROR");
        response.put("details", fieldErrors);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTaskNotFound(
        TaskNotFoundException ex
    ) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "NOT_FOUND");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(404).body(error);
    }
}
