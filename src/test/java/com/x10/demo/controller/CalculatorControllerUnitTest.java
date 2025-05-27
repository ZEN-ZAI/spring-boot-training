package com.x10.demo.controller;

import com.x10.demo.model.APIResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorControllerUnitTest {
    private CalculatorController controller;

    @BeforeEach
    void setUp() {
        controller = new CalculatorController();
    }

    @Test
    void testAdd() {
        ResponseEntity<APIResponse<Double>> response = controller.add(2.0, 3.0);
        assertEquals(200, response.getBody().getStatusCode());
        assertTrue(response.getBody().isSuccess());
        assertEquals(5.0, response.getBody().getData());
    }

    @Test
    void testSubtract() {
        ResponseEntity<APIResponse<Double>> response = controller.subtract(10.0, 4.0);
        assertEquals(200, response.getBody().getStatusCode());
        assertTrue(response.getBody().isSuccess());
        assertEquals(6.0, response.getBody().getData());
    }

    @Test
    void testMultiply() {
        ResponseEntity<APIResponse<Double>> response = controller.multiply(7.0, 6.0);
        assertEquals(200, response.getBody().getStatusCode());
        assertTrue(response.getBody().isSuccess());
        assertEquals(42.0, response.getBody().getData());
    }

    @Test
    void testDivide() {
        ResponseEntity<APIResponse<Double>> response = controller.divide(20.0, 4.0);
        assertEquals(200, response.getBody().getStatusCode());
        assertTrue(response.getBody().isSuccess());
        assertEquals(5.0, response.getBody().getData());
    }

    @Test
    void testDivideByZero() {
        ResponseEntity<APIResponse<Double>> response = controller.divide(20.0, 0.0);
        assertEquals(400, response.getBody().getStatusCode());
        assertFalse(response.getBody().isSuccess());
        assertNull(response.getBody().getData());
        assertEquals("Division by zero is not allowed", response.getBody().getMessage());
    }
}
