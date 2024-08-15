package com.heimdallauth.auth.kratosbackend.services;

import com.heimdallauth.auth.kratosbackend.dm.UserProfileDataManager;
import com.heimdallauth.auth.kratosbackend.documents.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserProfileService {
    private final UserProfileDataManager userProfileDataManager;

    @Autowired
    public UserProfileService(UserProfileDataManager userProfileDataManager) {
        this.userProfileDataManager = userProfileDataManager;
    }

    public UserProfile registerUser(UserProfile userProfile) {
        return userProfileDataManager.registerUser(userProfile);
    }

    public UserProfile getUserProfile(String username) {
        return userProfileDataManager.getUserProfile(username);
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
