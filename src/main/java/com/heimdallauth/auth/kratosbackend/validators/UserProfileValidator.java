package com.heimdallauth.auth.kratosbackend.validators;

import com.heimdallauth.auth.kratosbackend.documents.UserProfile;

public class UserProfileValidator {
    private static final String EMAIL_REGEX = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$";
    public static UserProfile cleanUserProfile(UserProfile userProfile) {
        if (userProfile.getUsername() == null || userProfile.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        if (userProfile.getEmail() == null || userProfile.getEmail().isEmpty() ||
        !userProfile.getEmail().matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Invalid email address");
        }

//        if (userProfile.getRole() == null || userProfile.getRole().isEmpty()) {
//            throw new IllegalArgumentException("Role cannot be null or empty");
//        }

        if (userProfile.getFirstName() == null || userProfile.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }

        if (userProfile.getLastName() == null || userProfile.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        return userProfile;
    }
}
