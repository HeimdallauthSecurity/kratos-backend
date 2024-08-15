package com.heimdallauth.auth.kratosbackend.dm;

import com.heimdallauth.auth.kratosbackend.documents.UserProfile;

import java.util.List;

public interface UserProfileDataManager {
    UserProfile registerUser(UserProfile userProfile);

    UserProfile getUserProfile(String username);

    UserProfile getUserProfileByEmail(String email);

    UserProfile getUserProfileById(String id);

    List<UserProfile> getAllActiveUsers();

    List<UserProfile> getAllInactiveUsers();

    UserProfile updateUserProfile(UserProfile updatedUserProfile);

    void disableUser(String username);

    void disableUserById(String id);

    void disableUserByEmail(String email);

}
