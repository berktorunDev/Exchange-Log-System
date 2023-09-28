package com.project.exchangelog.util.mapper;

import org.springframework.stereotype.Component;

import com.project.exchangelog.dto.UserDTO;
import com.project.exchangelog.model.User;

@Component
public class UserMapper {

    private final BaseMapper baseMapper;

    // Constructor with Dependency Injection
    public UserMapper(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * Converts a User entity to a UserDTO.
     *
     * @param entity The source User entity object to be converted.
     * @return The UserDTO object after conversion.
     */
    public UserDTO convertToDTO(User entity) {
        return baseMapper.convertToDTO(entity, UserDTO.class);
    }

    /**
     * Converts a UserDTO to a User entity.
     *
     * @param dto The source UserDTO object to be converted.
     * @return The User entity object after conversion.
     */
    public User convertToEntity(UserDTO dto) {
        return baseMapper.convertToEntity(dto, User.class);
    }
}
