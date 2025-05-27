package com.x10.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {
    private int statusCode;
    private boolean success;
    private String message;
    private T data;
}
