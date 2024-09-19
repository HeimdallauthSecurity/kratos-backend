package com.heimdallauth.auth.kratosbackend.services;

import com.heimdallauth.auth.kratosbackend.constants.ExceptionMessages;
import com.heimdallauth.auth.kratosbackend.dm.UserProfileDataManager;
import com.heimdallauth.auth.kratosbackend.documents.UserProfile;
import com.heimdallauth.auth.kratosbackend.dto.CreateUserProfileDTO;
import com.heimdallauth.auth.kratosbackend.exceptions.ResourceNotFound;
import com.heimdallauth.auth.kratosbackend.validators.UserProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class UserProfileService {
    private final UserProfileDataManager userProfileDataManager;

    @Autowired
    public UserProfileService(UserProfileDataManager userProfileDataManager) {
        this.userProfileDataManager = userProfileDataManager;
    }

    public UserProfile registerUser(CreateUserProfileDTO createUserProfilePayload) {
        UserProfile userProfile = UserProfile.builder()
                .creationTimestamp(Instant.now())
                .updateTimestamp(Instant.now())
                .firstName(createUserProfilePayload.getFirstName())
                .lastName(createUserProfilePayload.getLastName())
                .username(createUserProfilePayload.getUsername())
                .active(createUserProfilePayload.getIsActive())
                .email(createUserProfilePayload.getEmail())
                .build();
        UserProfile validatedUserProfile = UserProfileValidator.cleanUserProfile(userProfile);
        return userProfileDataManager.registerUser(validatedUserProfile);
    }

    public UserProfile getUserProfile(String username) {
        return userProfileDataManager.getUserProfile(username).orElseThrow(() -> new ResourceNotFound(ExceptionMessages.USER_RESOURCE_NOT_FOUND,"User Profile",username, UUID.randomUUID().toString() ));
    }

    public UserProfile getUserProfileByEmail(String email) {
        return userProfileDataManager.getUserProfileByEmail(email);
    }

    public UserProfile getUserProfileById(String id) {
        return userProfileDataManager.getUserProfileById(id);
    }

    public List<UserProfile> getAllActiveUsers() {
        return userProfileDataManager.getAllActiveUsers();
    }

    public List<UserProfile> getAllInactiveUsers() {
        return userProfileDataManager.getAllInactiveUsers();
    }

    public UserProfile updateUserProfile(UserProfile userProfile) {
        return userProfileDataManager.updateUserProfile(userProfile);
    }

    public void disableUser(String username) {
        userProfileDataManager.disableUser(username);
    }

    public void disableUserById(String id) {
        userProfileDataManager.disableUserById(id);
    }

    public void disableUserByEmail(String email) {
        userProfileDataManager.disableUserByEmail(email);
    }
}
