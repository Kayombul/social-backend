package com.social.social_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.social_backend.model.Profile;
import com.social.social_backend.model.User;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
    Optional<Profile> findByUser(User user);
}
