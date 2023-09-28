package com.project.exchangelog.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.exchangelog.dto.PostDTO;
import com.project.exchangelog.log.Log;
import com.project.exchangelog.model.Post;
import com.project.exchangelog.repository.PostRepository;
import com.project.exchangelog.util.mapper.PostMapper;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final RabbitMQService rabbitMQService;

    public PostService(PostRepository postRepository, PostMapper postMapper, RabbitMQService rabbitMQService) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.rabbitMQService = rabbitMQService;
    }

    // Get a list of all posts and map them to DTOs
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(postMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get a post by ID and map it to a DTO
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            return postMapper.convertToDTO(post);
        }
        return null;
    }

    // Create a new post, save it, and send a log message to RabbitMQ
    public PostDTO createPost(PostDTO postDTO) {
        Post post = postMapper.convertToEntity(postDTO);
        post = postRepository.save(post);

        // Create a log with operation information and send it to RabbitMQ
        Log log = new Log(new Date(), "CREATE", "POST", post);
        rabbitMQService.sendLogToQueue(log);

        return postMapper.convertToDTO(post);
    }

    // Update an existing post, save it, and send a log message to RabbitMQ
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post existingPost = postRepository.findById(id).orElse(null);
        if (existingPost != null) {
            existingPost.setTitle(postDTO.getTitle());
            existingPost.setContent(postDTO.getContent());

            existingPost = postRepository.save(existingPost);

            // Create a log with operation information and send it to RabbitMQ
            Log log = new Log(new Date(), "UPDATE", "POST", existingPost);
            rabbitMQService.sendLogToQueue(log);

            return postMapper.convertToDTO(existingPost);
        }
        return null;
    }

    // Delete a post by ID, send a log message to RabbitMQ with the deleted object
    // information
    public void deletePost(Long id) {
        postRepository.deleteById(id);

        Map<String, Long> deletedObject = new HashMap<>();
        deletedObject.put("deletedObjectId", id);

        // Create a log with operation information and send it to RabbitMQ
        Log log = new Log(new Date(), "DELETE", "POST", deletedObject);
        rabbitMQService.sendLogToQueue(log);
    }
}