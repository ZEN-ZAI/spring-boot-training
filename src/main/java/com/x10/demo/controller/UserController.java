package com.x10.demo.controller;

import com.x10.demo.model.APIResponse;
import com.x10.demo.model.CreateUserRequest;
import com.x10.demo.model.CreateUserResponse;
import com.x10.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<CreateUserResponse>> createUser(
            @Valid @RequestBody CreateUserRequest payload) {
        try {
            log.info("Received request to create user: username={}, email={}, mobile={}, age={}", payload.getUsername(),
                    payload.getEmail(), payload.getMobile(), payload.getAge());
            CreateUserResponse createdUser = userService.createUser(payload);
            APIResponse<CreateUserResponse> response = new APIResponse<CreateUserResponse>(
                    201,
                    true,
                    "User created successfully",
                    createdUser);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            APIResponse<CreateUserResponse> errorResponse = new APIResponse<>(
                    400,
                    false,
                    ex.getMessage(),
                    null);
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
