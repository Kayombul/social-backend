package com.social.social_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.social.social_backend.model.Post;
import com.social.social_backend.service.PostService;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create/{username}")
    public Post createPost(
            @PathVariable String username,
            @RequestParam String content,
            @RequestParam(required = false) String imageUrl
    ) {
        return postService.createdPost(username, content, imageUrl);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{username}")
    public List<Post> getPostsByUser(@PathVariable String username) {
        return postService.getPostByUser(username);
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId) {
        boolean deleted = postService.deletePost(postId);
        return deleted ? "Post deleted successfully!" : "Post not found!";
    }
}
