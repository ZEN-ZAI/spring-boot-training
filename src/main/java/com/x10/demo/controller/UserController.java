package com.x10.demo.controller;

import com.x10.demo.model.APIResponse;
import com.x10.demo.model.CreateUserRequest;
import com.x10.demo.model.UserResponse;
import com.x10.demo.service.UserService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

        private final UserService userService;

        @PostMapping
        public ResponseEntity<APIResponse<UserResponse>> createUser(
                        @Valid @RequestBody CreateUserRequest payload) {
                log.info("Received request to create user: username={}, email={}, mobile={}, age={}",
                                payload.getUsername(),
                                payload.getEmail(), payload.getMobile(), payload.getAge());

                UserResponse createdUser = userService.createUser(payload);
                APIResponse<UserResponse> response = new APIResponse<UserResponse>(
                                201,
                                true,
                                "User created successfully",
                                createdUser);
                return ResponseEntity.ok(response);
        }

        @GetMapping
        public ResponseEntity<APIResponse<List<UserResponse>>> findAll() {
                log.info("Received request to find all users");

                String message = "Total users: " + userService.getAllUsers().size();
                APIResponse<List<UserResponse>> response = new APIResponse<List<UserResponse>>(200, true,
                                message,
                                userService.getAllUsers());
                return ResponseEntity.ok(response);

        }

        @GetMapping("/{id}")
        public ResponseEntity<APIResponse<UserResponse>> findById(@PathVariable Long id) {
                log.info("Received request to find user by ID: {}", id);

                UserResponse user = userService.getUserById(id);
                APIResponse<UserResponse> response = new APIResponse<UserResponse>(
                                user != null ? 200 : 404,
                                user != null,
                                user != null ? "User found" : "User not found",
                                user);
                return ResponseEntity.ok(response);
        }

        @PutMapping("/{id}")
        public ResponseEntity<APIResponse<UserResponse>> updateUser(
                        @PathVariable Long id, @RequestBody CreateUserRequest payload) {
                log.info("Received request to update user: username={}, email={}, mobile={}, age={}",
                                payload.getUsername(),
                                payload.getEmail(), payload.getMobile(), payload.getAge());

                UserResponse updatedUser = userService.updateUser(id, payload);
                APIResponse<UserResponse> response = new APIResponse<UserResponse>(
                                200,
                                true,
                                "User updated successfully",
                                updatedUser);
                return ResponseEntity.ok(response);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<APIResponse<String>> deleteUser(@PathVariable Long id) {
                log.info("Received request to delete user with ID: {}", id);

                Boolean success = userService.deleteUser(id);
                APIResponse<String> response = new APIResponse<String>(
                                success ? 200 : 404,
                                success,
                                success ? "User deleted successfully" : "User not found",
                                null);

                return ResponseEntity.ok(response);
        }
}
