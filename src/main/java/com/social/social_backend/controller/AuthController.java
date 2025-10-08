package com.social.social_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.social_backend.model.User;
import com.social.social_backend.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // optional if you'll call it from front-end later
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.registerUser(user.getUsername(), user.getPassword());
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        boolean isValid = userService.validateUser(user.getUsername(), user.getPassword());
        if (isValid) {
            return "Login successful!";
        } else {
            return "Invalid username or password!";
        }
    }
}
