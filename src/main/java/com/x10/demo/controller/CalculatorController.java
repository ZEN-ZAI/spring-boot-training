package com.x10.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.x10.demo.model.APIResponse;
import com.x10.demo.service.ICalculatorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

    private final ICalculatorService calculatorService;

    @Autowired
    public CalculatorController(ICalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/add")
    public ResponseEntity<APIResponse<Double>> add(@RequestParam(value = "a", required = true) double a,
            @RequestParam double b) {
        double result = calculatorService.add(a, b);
        log.info("Adding numbers: a={}, b={}", a, b);
        APIResponse<Double> response = new APIResponse<Double>(200, true, "Addition successful", result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/subtract")
    public ResponseEntity<APIResponse<Double>> subtract(@RequestParam double a, @RequestParam double b) {
        double result = calculatorService.subtract(a, b);
        log.info("Subtracting numbers: a={}, b={}", a, b);
        APIResponse<Double> response = new APIResponse<Double>(200, true, "Subtraction successful", result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/multiply")
    public ResponseEntity<APIResponse<Double>> multiply(@RequestParam double a, @RequestParam double b) {
        double result = calculatorService.multiply(a, b);
        log.info("Multiplying numbers: a={}, b={}", a, b);
        APIResponse<Double> response = new APIResponse<Double>(200, true, "Multiplication successful", result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/divide")
    public ResponseEntity<APIResponse<Double>> divide(@RequestParam double a, @RequestParam double b) {
        try {
            double result = calculatorService.divide(a, b);
            log.info("Dividing numbers: a={}, b={}", a, b);
            APIResponse<Double> response = new APIResponse<>(200, true, "Division successful", result);
            return ResponseEntity.ok(response);
        } catch (ArithmeticException e) {
            APIResponse<Double> response = new APIResponse<>(400, false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

}
