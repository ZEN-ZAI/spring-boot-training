package com.x10.demo.service;

import com.x10.demo.entity.UserEntity;
import com.x10.demo.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserServiceTest {
    @Autowired
    private IUserRepository userRepository;

    @Test
    void createUser() {
        UserEntity user = UserEntity.builder()
                .username("testuser")
                .password("testpass123")
                .email("test@email.com")
                .mobile("0123456789")
                .age(25)
                .build();
        UserEntity saved = userRepository.save(user);
        assertNotNull(saved.getId());
    }

    @Test
    void getUserById_notFound() {
        Optional<UserEntity> user = userRepository.findById(999L);
        assertTrue(user.isEmpty());
    }
}
