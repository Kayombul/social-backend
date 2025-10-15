package com.social.social_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.social_backend.model.Profile;
import com.social.social_backend.model.User;
import com.social.social_backend.repository.ProfileRepository;
import com.social.social_backend.repository.UserRepository;


@Service
public class ProfileService {
    
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

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

    public boolean deleteProfile(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            return false;
        }

        Optional<Profile> profile = profileRepository.findByUser(user.get());
        if(profile.isPresent()){
            profileRepository.delete(profile.get());
            return true;
        }
        return false;
    }

    // public boolean updateProfile(String username, Profile updatProfile) {
    //     Optional<User> userOpt = UserRepository.findByUsername(username);
    //     if (userOpt.isEmpty()) return false;

    //     Optional<Profile> profileOpt = profileRepository.findByUser(userOpt.get());
    //     if (profileOpt.isEmpty()) return false;

    //     Profile existingProfile = profileOpt.get();
    //     existingProfile.setFullName(updateProfile.getFullName());
    //     existingProfile.setBio(updateProfile.getBio());
    //     existingProfile.setAvatarUrl(updateProfile.getAvatarUrl());
    //     profileRepository.save(existingProfile);
    // }
}
