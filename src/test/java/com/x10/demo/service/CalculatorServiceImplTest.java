package com.x10.demo.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceImplTest {
    private final ICalculatorService service = new CalculatorServiceImpl();

    @Test
    void testAdd() {
        assertEquals(5.0, service.add(2.0, 3.0));
    }

    @Test
    void testSubtract() {
        assertEquals(6.0, service.subtract(10.0, 4.0));
    }

    @Test
    void testMultiply() {
        assertEquals(42.0, service.multiply(7.0, 6.0));
    }

    @Test
    void testDivide() {
        assertEquals(5.0, service.divide(20.0, 4.0));
    }

    @Test
    void testDivideByZeroThrows() {
        Exception exception = assertThrows(ArithmeticException.class, () -> service.divide(20.0, 0.0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
}
