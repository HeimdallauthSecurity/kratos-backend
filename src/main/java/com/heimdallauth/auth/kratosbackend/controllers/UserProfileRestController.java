package com.heimdallauth.auth.kratosbackend.controllers;

import com.heimdallauth.auth.kratosbackend.documents.UserProfile;
import com.heimdallauth.auth.kratosbackend.dto.CreateUserProfileDTO;
import com.heimdallauth.auth.kratosbackend.dto.UserInfoDTO;
import com.heimdallauth.auth.kratosbackend.services.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Tag(name = "User Profile", description = "Operations related to user profiles")
public class UserProfileRestController {

    private final UserProfileService userService;
    private final ModelMapper userModelMapper;

    @Autowired
    public UserProfileRestController(UserProfileService userService, ModelMapper userModelMapper) {
        this.userService = userService;
        this.userModelMapper = userModelMapper;
    }

    @PostMapping
    @Operation(summary = "Register a new user")
    public UserInfoDTO registerUser(@RequestBody CreateUserProfileDTO userProfilePayload) {
        return userModelMapper.map(userService.registerUser(userProfilePayload), UserInfoDTO.class);
    }

    @GetMapping("/{username}")
    @Operation(summary = "Get user profile by username")
    public UserInfoDTO getUserProfile(@PathVariable String username) {
        return userModelMapper.map(userService.getUserProfile(username), UserInfoDTO.class);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get user profile by email")
    public UserInfoDTO getUserProfileByEmail(@PathVariable String email) {
        return userModelMapper.map(userService.getUserProfileByEmail(email), UserInfoDTO.class);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Get user profile by ID")
    public UserInfoDTO getUserProfileById(@PathVariable String id) {
        return userModelMapper.map(userService.getUserProfileById(id), UserInfoDTO.class);
    }

    @GetMapping("/active")
    @Operation(summary = "Get all active users")
    public List<UserInfoDTO> getAllActiveUsers() {
        return userService.getAllActiveUsers().stream()
                .map(user -> userModelMapper.map(user, UserInfoDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/inactive")
    @Operation(summary = "Get all inactive users")
    public List<UserInfoDTO> getAllInactiveUsers() {
        return userService.getAllInactiveUsers().stream()
                .map(user -> userModelMapper.map(user, UserInfoDTO.class))
                .collect(Collectors.toList());
    }

    @PutMapping
    @Operation(summary = "Update user profile")
    public UserInfoDTO updateUserProfile(@RequestBody UserProfile userProfile) {
        return userModelMapper.map(userService.updateUserProfile(userProfile), UserInfoDTO.class);
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