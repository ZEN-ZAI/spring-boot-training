package com.x10.demo.config;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.x10.demo.model.APIResponse;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    // @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    // public ResponseEntity<APIResponse<String>> handleMethodArgumentTypeMismatchException(
    //         MethodArgumentTypeMismatchException ex) {
    //     String errorMessage = String.format("Invalid value '%s' for parameter '%s'. Expected type: %s",
    //             ex.getValue(), ex.getName(), ex.getRequiredType().getSimpleName());
    //     APIResponse<String> response = new APIResponse<>(400, false, errorMessage, null);
    //     return ResponseEntity.badRequest().body(response);
    // }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        String errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        APIResponse<String> response = new APIResponse<>(400, false, "Validation failed: " + errorMessages, null);
        return ResponseEntity.badRequest().body(response);
    }
}
