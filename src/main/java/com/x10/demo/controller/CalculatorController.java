package com.x10.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.x10.demo.model.APIResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

    @PostMapping("/add")
    public ResponseEntity<APIResponse<Double>> add(@RequestParam(value = "a", required = true) double a,
            @RequestParam double b) {
        double result = a + b;
        log.info("Adding numbers: a={}, b={}", a, b);
        APIResponse<Double> response = new APIResponse<Double>(200, true, "Addition successful", result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/subtract")
    public ResponseEntity<APIResponse<Double>> subtract(@RequestParam double a, @RequestParam double b) {
        double result = a - b;
        log.info("Subtracting numbers: a={}, b={}", a, b);
        APIResponse<Double> response = new APIResponse<Double>(200, true, "Subtraction successful", result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/multiply")
    public ResponseEntity<APIResponse<Double>> multiply(@RequestParam double a, @RequestParam double b) {
        double result = a * b;
        log.info("Multiplying numbers: a={}, b={}", a, b);
        APIResponse<Double> response = new APIResponse<Double>(200, true, "Multiplication successful", result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/divide")
    public ResponseEntity<APIResponse<Double>> divide(@RequestParam double a, @RequestParam double b) {
        if (b == 0) {
            APIResponse<Double> response = new APIResponse<>(400, false, "Division by zero is not allowed", null);
            return ResponseEntity.badRequest().body(response);
        }
        double result = a / b;
        log.info("Dividing numbers: a={}, b={}", a, b);
        APIResponse<Double> response = new APIResponse<>(200, true, "Division successful", result);
        return ResponseEntity.ok(response);
    }

}
