package com.project.exchangelog.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.exchangelog.dto.UserDTO;
import com.project.exchangelog.log.Log;
import com.project.exchangelog.model.User;
import com.project.exchangelog.repository.UserRepository;
import com.project.exchangelog.util.mapper.UserMapper;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RabbitMQService rabbitMQService;

    public UserService(UserRepository userRepository, UserMapper userMapper, RabbitMQService rabbitMQService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.rabbitMQService = rabbitMQService;
    }

    // Get all users
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get a user by ID
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return userMapper.convertToDTO(user);
        }
        return null;
    }

    // Create a new user
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.convertToEntity(userDTO);
        user = userRepository.save(user);

        // Create a log for the user creation and send it to RabbitMQ
        Log log = new Log(new Date(), "CREATE", "USER", user);
        rabbitMQService.sendLogToQueue(log);

        return userMapper.convertToDTO(user);
    }

    // Update an existing user
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(userDTO.getUsername());
            existingUser.setEmail(userDTO.getEmail());
            existingUser.setRole(userDTO.getRole());
            existingUser = userRepository.save(existingUser);

            // Create a log for the user update and send it to RabbitMQ
            Log log = new Log(new Date(), "UPDATE", "USER", existingUser);
            rabbitMQService.sendLogToQueue(log);

            return userMapper.convertToDTO(existingUser);
        }
        return null;
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

        // Create a log for the user deletion and send it to RabbitMQ
        Map<String, Long> deletedObject = new HashMap<>();
        deletedObject.put("deletedObjectId", id);
        Log log = new Log(new Date(), "DELETE", "USER", deletedObject);
        rabbitMQService.sendLogToQueue(log);
    }
}
