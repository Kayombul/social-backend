package com.social.social_backend.repository;

import com.social.social_backend.model.Post;
import com.social.social_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
}
