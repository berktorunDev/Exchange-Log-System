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

import com.project.exchangelog.dto.UserDTO;
import com.project.exchangelog.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public List<UserDTO> getAllUsers() {
        // Handles HTTP GET requests to retrieve a list of all users.
        return userService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        // Handles HTTP GET requests to retrieve a specific user by ID.
        return userService.getUserById(id);
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        // Handles HTTP POST requests to create a new user.
        return userService.createUser(userDTO);
    }

    @PutMapping("/update/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        // Handles HTTP PUT requests to update an existing user by ID.
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        // Handles HTTP DELETE requests to delete a user by ID.
        userService.deleteUser(id);
    }
}
