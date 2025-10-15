package com.social.social_backend.service;

import com.social.social_backend.model.Post;
import com.social.social_backend.model.User;
import com.social.social_backend.repository.PostRepository;
import com.social.social_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Post createdPost(String username, String content, String imageUrl) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw new RuntimeException("Uers not found");
        }
        Post post = new Post(user.get(), content, imageUrl);
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    public List<Post> getPostByUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(postRepository::findByUser).orElse(List.of());
    }

    public boolean deletePost(Long postId) {
        if (postRepository.existsById(postId)) {
            postRepository.deleteById(postId);
            return true;
        }
        return false;
    }
}
