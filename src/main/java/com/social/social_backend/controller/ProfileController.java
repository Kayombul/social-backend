package com.social.social_backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.social.social_backend.model.Profile;
import com.social.social_backend.model.User;
import com.social.social_backend.repository.UserRepository;
import com.social.social_backend.service.ProfileService;


@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "*")
public class ProfileController {
    
    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create/{username}")
    public String createProfile(
            @PathVariable String username,
            @RequestParam String fullName,
            @RequestParam String bio,
            @RequestParam String email
    ){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            return "User not found";
        }
        profileService.creatProfile(user.get(), fullName, bio, email);
        return "Profile created successfully";
    }

    @GetMapping("/{username}")
    public Optional<Profile> getProfile(@PathVariable String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.flatMap(profileService::getProfileByUser);
    }
    
}
