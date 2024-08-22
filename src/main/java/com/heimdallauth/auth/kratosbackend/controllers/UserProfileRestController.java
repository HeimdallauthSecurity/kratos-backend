package com.heimdallauth.auth.kratosbackend.controllers;

import com.heimdallauth.auth.kratosbackend.dm.UserProfileDataManager;
import com.heimdallauth.auth.kratosbackend.documents.UserProfile;
import com.heimdallauth.auth.kratosbackend.dto.CreateUserProfileDTO;
import com.heimdallauth.auth.kratosbackend.services.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User Profile", description = "Operations related to user profiles")
public class UserProfileRestController {

    private final UserProfileService userService;

    @Autowired
    public UserProfileRestController(UserProfileService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Register a new user")
    public UserProfile registerUser(@RequestBody CreateUserProfileDTO userProfilePayload) {
        return userService.registerUser(userProfilePayload);
    }

    @GetMapping("/{username}")
    @Operation(summary = "Get user profile by username")
    public UserProfile getUserProfile(@PathVariable String username) {
        return userService.getUserProfile(username);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get user profile by email")
    public UserProfile getUserProfileByEmail(@PathVariable String email) {
        return userService.getUserProfileByEmail(email);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Get user profile by ID")
    public UserProfile getUserProfileById(@PathVariable String id) {
        return userService.getUserProfileById(id);
    }

    @GetMapping("/active")
    @Operation(summary = "Get all active users")
    public List<UserProfile> getAllActiveUsers() {
        return userService.getAllActiveUsers();
    }

    @GetMapping("/inactive")
    @Operation(summary = "Get all inactive users")
    public List<UserProfile> getAllInactiveUsers() {
        return userService.getAllInactiveUsers();
    }

    @PutMapping
    @Operation(summary = "Update user profile")
    public UserProfile updateUserProfile(@RequestBody UserProfile userProfile) {
        return userService.updateUserProfile(userProfile);
    }

    @PutMapping("/disable/{username}")
    @Operation(summary = "Disable user by username")
    public void disableUser(@PathVariable String username) {
        userService.disableUser(username);
    }

    @PutMapping("/disable/id/{id}")
    @Operation(summary = "Disable user by ID")
    public void disableUserById(@PathVariable String id) {
        userService.disableUserById(id);
    }

    @PutMapping("/disable/email/{email}")
    @Operation(summary = "Disable user by email")
    public void disableUserByEmail(@PathVariable String email) {
        userService.disableUserByEmail(email);
    }
}