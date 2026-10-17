package com.br.manager.domain.operational.mapper;

import com.br.manager.domain.operational.dto.UserResponseDTO;
import com.br.manager.domain.operational.dto.UserRequestDTO;
import com.br.manager.domain.operational.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserResponseDTO toDto(User u) {
        if (u == null) return null;
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(u.getId());
        dto.setName(u.getName());
        dto.setEmail(u.getEmail());
        dto.setPhone(u.getPhone());
        dto.setActive(u.getActive());
        dto.setLastLoginAt(u.getLastLoginAt());
        return dto;
    }

    public List<UserResponseDTO> toDtoList(List<User> list) {
        if (list == null) return null;
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public User toEntity(UserResponseDTO dto) {
        if (dto == null) return null;
        User u = new User();
        u.setId(dto.getId());
        u.setName(dto.getName());
        u.setEmail(dto.getEmail());
        u.setPhone(dto.getPhone());
        u.setActive(dto.getActive());
        u.setLastLoginAt(dto.getLastLoginAt());
        return u;
    }

    public User toEntity(UserRequestDTO dto) {
        if (dto == null) return null;
        User u = new User();
        // Map id from retaguarda (if present) so JPA knows the identifier before persisting
        if (dto.getId() != null) {
            u.setId(dto.getId());
        }
        u.setName(dto.getName());
        u.setEmail(dto.getEmail());
        u.setPasswordHash(dto.getPasswordHash());
        u.setPhone(dto.getPhone());
        if (dto.getActive() != null) {
            u.setActive(dto.getActive());
        }
        u.setLastLoginAt(dto.getLastLoginAt());
        return u;
    }

    public List<User> toEntityList(List<UserRequestDTO> list) {
        if (list == null) return null;
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
