package com.heimdallauth.auth.kratosbackend;

import com.heimdallauth.auth.kratosbackend.documents.UserProfile;
import com.heimdallauth.auth.kratosbackend.services.UserProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@Disabled
class UserProfileRestControllerTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserProfileService userService;

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeEach
    void setUp() {
        // Initialize the database or any other setup if needed
    }

    @Test
    void registerUser() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername("testuser");
        when(userService.registerUser(any(UserProfile.class))).thenReturn(userProfile);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"testuser\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    void getUserProfile() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername("testuser");
        when(userService.getUserProfile("testuser")).thenReturn(userProfile);

        mockMvc.perform(get("/users/testuser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    void getUserProfileByEmail() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail("test@example.com");
        when(userService.getUserProfileByEmail("test@example.com")).thenReturn(userProfile);

        mockMvc.perform(get("/users/email/test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    void getUserProfileById() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setId("123");
        when(userService.getUserProfileById("123")).thenReturn(userProfile);

        mockMvc.perform(get("/users/id/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"));
    }

    @Test
    void getAllActiveUsers() throws Exception {
        List<UserProfile> activeUsers = Arrays.asList(new UserProfile(), new UserProfile());
        when(userService.getAllActiveUsers()).thenReturn(activeUsers);

        mockMvc.perform(get("/users/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getAllInactiveUsers() throws Exception {
        List<UserProfile> inactiveUsers = Arrays.asList(new UserProfile(), new UserProfile());
        when(userService.getAllInactiveUsers()).thenReturn(inactiveUsers);

        mockMvc.perform(get("/users/inactive"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void updateUserProfile() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername("updateduser");
        when(userService.updateUserProfile(any(UserProfile.class))).thenReturn(userProfile);

        mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"updateduser\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("updateduser"));
    }

    @Test
    void disableUser() throws Exception {
        mockMvc.perform(put("/users/disable/testuser"))
                .andExpect(status().isOk());
    }

    @Test
    void disableUserById() throws Exception {
        mockMvc.perform(put("/users/disable/id/123"))
                .andExpect(status().isOk());
    }

    @Test
    void disableUserByEmail() throws Exception {
        mockMvc.perform(put("/users/disable/email/test@example.com"))
                .andExpect(status().isOk());
    }
}