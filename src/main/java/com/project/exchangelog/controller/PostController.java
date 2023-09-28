package com.project.exchangelog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.exchangelog.dto.PostDTO;
import com.project.exchangelog.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
    // This class defines a RESTful API controller for managing posts.

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/getAll")
    public List<PostDTO> getAllPosts() {
        // Handles HTTP GET requests to retrieve a list of all posts.
        return postService.getAllPosts();
    }

    @GetMapping("/get/{id}")
    public PostDTO getPostById(@PathVariable Long id) {
        // Handles HTTP GET requests to retrieve a specific post by ID.
        return postService.getPostById(id);
    }

    @PostMapping("/create")
    public PostDTO createPost(@RequestBody PostDTO postDTO) {
        // Handles HTTP POST requests to create a new post.
        return postService.createPost(postDTO);
    }

    @PutMapping("/update/{id}")
    public PostDTO updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        // Handles HTTP PUT requests to update an existing post by ID.
        return postService.updatePost(id, postDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable Long id) {
        // Handles HTTP DELETE requests to delete a post by ID.
        postService.deletePost(id);
    }
}