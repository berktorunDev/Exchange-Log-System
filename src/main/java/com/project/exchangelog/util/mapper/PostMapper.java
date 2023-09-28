package com.project.exchangelog.util.mapper;

import org.springframework.stereotype.Component;

import com.project.exchangelog.dto.PostDTO;
import com.project.exchangelog.model.Post;

@Component
public class PostMapper {

    private final BaseMapper baseMapper;

    // Constructor with Dependency Injection
    public PostMapper(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * Converts a Post entity to a PostDTO.
     *
     * @param entity The source Post entity object to be converted.
     * @return The PostDTO object after conversion.
     */
    public PostDTO convertToDTO(Post entity) {
        return baseMapper.convertToDTO(entity, PostDTO.class);
    }

    /**
     * Converts a PostDTO to a Post entity.
     *
     * @param dto The source PostDTO object to be converted.
     * @return The Post entity object after conversion.
     */
    public Post convertToEntity(PostDTO dto) {
        return baseMapper.convertToEntity(dto, Post.class);
    }
}
