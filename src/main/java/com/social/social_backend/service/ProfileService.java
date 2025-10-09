package com.social.social_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.social_backend.model.Profile;
import com.social.social_backend.model.User;
import com.social.social_backend.repository.ProfileRepository;

@Service
public class ProfileService {
    
    @Autowired
    private ProfileRepository profileRepository;

    public Profile creatProfile(User user, String fullName, String bio, String email) {
        Profile profile = new Profile(fullName, bio, email, user);
        return profileRepository.save(profile);
    }

    public Optional<Profile> getProfileByUser(User user) {
        return profileRepository.findByUser(user);
    }

    public Profile updateProfile(Profile existing, String fullName, String bio, String email, String profileImage) {
        existing.setFullName(fullName);
        existing.setBio(bio);
        existing.setEmail(email);
        existing.setProfileImage(profileImage);
        return profileRepository.save(existing);
    }
}
