package com.social.social_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String bio;
    private String profileImage; // store URL or base64 string later
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName= "id")
    private User user;

    public Profile() {}

    public Profile(String fullName, String bio, String email, User user) {
        this.fullName = fullName;
        this.bio = bio;
        this.email = email;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getProfileImage() { return profileImage; }
    public void setProfileImage(String profileImage) { this.profileImage = profileImage; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
}
