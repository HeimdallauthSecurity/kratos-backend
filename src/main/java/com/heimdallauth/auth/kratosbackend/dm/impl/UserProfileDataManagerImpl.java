package com.heimdallauth.auth.kratosbackend.dm.impl;

import com.heimdallauth.auth.kratosbackend.dm.UserProfileDataManager;
import com.heimdallauth.auth.kratosbackend.documents.UserProfile;
import com.heimdallauth.auth.kratosbackend.validators.UserProfileValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@ConditionalOnProperty(name = "heimdallauth.data-manager", havingValue = "mongo")
public class UserProfileDataManagerImpl implements UserProfileDataManager {
    private static final String USER_COLLECTION = "user_profile_collection";
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserProfileDataManagerImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public UserProfile registerUser(UserProfile userProfile) {
        UserProfile cleanedUserProfile = UserProfileValidator.cleanUserProfile(userProfile);
        UserProfile savedUserProfile = this.mongoTemplate.save(cleanedUserProfile, USER_COLLECTION);
        log.info("User profile saved successfully: {}", savedUserProfile);
        return savedUserProfile;
    }

    @Override
    public UserProfile getUserProfile(String username) {
        return mongoTemplate.findOne(
                Query.query(Criteria.where("username").is(username)),
                UserProfile.class,
                USER_COLLECTION
        );
    }

    @Override
    public UserProfile getUserProfileByEmail(String email) {
        return mongoTemplate.findOne(
                Query.query(Criteria.where("email").is(email)),
                UserProfile.class,
                USER_COLLECTION
        );
    }

    @Override
    public UserProfile getUserProfileById(String id) {
        return mongoTemplate.findById(id, UserProfile.class, USER_COLLECTION);
    }

    @Override
    public List<UserProfile> getAllActiveUsers() {
        return mongoTemplate.find(
                Query.query(Criteria.where("active").is(true)),
                UserProfile.class,
                USER_COLLECTION
        );
    }

    @Override
    public List<UserProfile> getAllInactiveUsers() {
        return mongoTemplate.find(
                Query.query(Criteria.where("active").is(false)),
                UserProfile.class,
                USER_COLLECTION
        );
    }

    @Override
    public UserProfile updateUserProfile(UserProfile updatedUserProfile) {
        return mongoTemplate.save(updatedUserProfile, USER_COLLECTION);
    }

    @Override
    public void disableUser(String username) {
        UserProfile userProfile = getUserProfile(username);
        if (userProfile != null) {
            userProfile.setActive(false);
            updateUserProfile(userProfile);
        }
    }

    @Override
    public void disableUserById(String id) {
        UserProfile userProfile = getUserProfileById(id);
        if (userProfile != null) {
            userProfile.setActive(false);
            updateUserProfile(userProfile);
        }
    }

    @Override
    public void disableUserByEmail(String email) {
        UserProfile userProfile = getUserProfileByEmail(email);
        if (userProfile != null) {
            userProfile.setActive(false);
            updateUserProfile(userProfile);
        }
    }
}
