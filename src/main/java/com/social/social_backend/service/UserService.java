package com.social.social_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.social.social_backend.model.User;
import com.social.social_backend.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User registerUser(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }
        String encodedPassword = encoder.encode(password);
        User user = new User(username, encodedPassword);
        return userRepository.save(user);
    }

    public boolean loginUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && encoder.matches(password, user.get().getPassword());
    }

    public boolean validateUser(String username, String rawPassword) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return encoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }
}
