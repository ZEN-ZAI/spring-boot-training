package com.x10.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotBlank(message = "Username is required and cannot be blank.")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters long.")
    private String username;

    @NotBlank(message = "Password is required and cannot be blank.")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters long.")
    private String password;

    @NotBlank(message = "Email is required and cannot be blank.")
    @Email(message = "Email format is invalid.")
    private String email;

    @NotBlank(message = "Mobile number is required and cannot be blank.")
    @Pattern(regexp = "^0\\d{9}$", message = "Mobile number must start with 0 and be exactly 10 digits.")
    private String mobile;

    @NotNull(message = "Age is required.")
    @Min(value = 18, message = "Age must be at least 18.")
    @Max(value = 100, message = "Age must be at most 100.")
    private Integer age;
}
