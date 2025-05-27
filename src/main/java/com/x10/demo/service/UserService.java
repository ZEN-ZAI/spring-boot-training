package com.x10.demo.service;

import com.x10.demo.model.CreateUserRequest;
import com.x10.demo.model.CreateUserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public CreateUserResponse createUser(CreateUserRequest model) {
        return new CreateUserResponse(
                model.getUsername(),
                model.getEmail(),
                model.getMobile(),
                model.getAge());
    }
}
