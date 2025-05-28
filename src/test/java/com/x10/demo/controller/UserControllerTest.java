package com.x10.demo.controller;

import com.x10.demo.model.CreateUserRequest;
import com.x10.demo.model.UserResponse;
import com.x10.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void getAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createUser() throws Exception {
        CreateUserRequest req = new CreateUserRequest();
        req.setUsername("testuser");
        req.setPassword("testpass123");
        req.setEmail("test@email.com");
        req.setMobile("0123456789");
        req.setAge(25);
        UserResponse user = UserResponse.builder()
                .id(1L)
                .username("testuser")
                .email("test@email.com")
                .mobile("0123456789")
                .age(25)
                .build();
        when(userService.createUser(Mockito.any(CreateUserRequest.class))).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"username\":\"testuser\",\"password\":\"testpass123\",\"email\":\"test@email.com\",\"mobile\":\"0123456789\",\"age\":25}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
