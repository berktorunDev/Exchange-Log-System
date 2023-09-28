package com.project.exchangelog.dto;

public class UserDTO {
    // This class represents a Data Transfer Object (DTO) for User entities.

    private Long id;
    private String username;
    private String email;
    private String role;

    // Getters and setters for the class properties.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
