package com.x10.demo.service;

import com.x10.demo.entity.UserEntity;
import com.x10.demo.model.CreateUserRequest;
import com.x10.demo.model.UserResponse;
import com.x10.demo.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

        private final IUserRepository userRepository;

        public UserResponse createUser(CreateUserRequest model) {
                UserEntity user = UserEntity.builder()
                                .username(model.getUsername())
                                .password(model.getPassword())
                                .email(model.getEmail())
                                .mobile(model.getMobile())
                                .age(model.getAge())
                                .build();

                UserEntity savedUser = this.userRepository.save(user);

                return UserResponse.builder()
                                .id(savedUser.getId())
                                .username(savedUser.getUsername())
                                .email(savedUser.getEmail())
                                .mobile(savedUser.getMobile())
                                .age(savedUser.getAge())
                                .build();
        }

        public List<UserResponse> getAllUsers() {
                List<UserEntity> users = userRepository.findAll();
                return users.stream()
                                .map(user -> UserResponse.builder()
                                                .id(user.getId())
                                                .username(user.getUsername())
                                                .email(user.getEmail())
                                                .mobile(user.getMobile())
                                                .age(user.getAge())
                                                .build())
                                .toList();
        }

        public UserResponse getUserById(Long id) {
                UserEntity user = userRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

                return UserResponse.builder()
                                .id(user.getId())
                                .username(user.getUsername())
                                .email(user.getEmail())
                                .mobile(user.getMobile())
                                .age(user.getAge())
                                .build();
        }

        public UserResponse updateUser(Long id, CreateUserRequest model) {
                UserEntity user = userRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

                user.setUsername(model.getUsername());
                user.setEmail(model.getEmail());
                user.setMobile(model.getMobile());
                user.setAge(model.getAge());

                UserEntity updatedUser = userRepository.save(user);

                return UserResponse.builder()
                                .id(updatedUser.getId())
                                .username(updatedUser.getUsername())
                                .email(updatedUser.getEmail())
                                .mobile(updatedUser.getMobile())
                                .age(updatedUser.getAge())
                                .build();
        }

        public Boolean deleteUser(Long id) {
                userRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
                userRepository.deleteById(id);
                Boolean exists = userRepository.existsById(id);
                return !exists;
        }
}
