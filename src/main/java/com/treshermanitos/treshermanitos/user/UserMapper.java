package com.treshermanitos.treshermanitos.user;

import com.treshermanitos.treshermanitos.config.BaseMapper;

public class UserMapper implements BaseMapper<User, UserDTO> {

    @Override
    public User dtoToEntity(UserDTO dto) {
        return User.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .province(dto.getProvince())
                .city(dto.getCity())
                .role(dto.getRole())
                .build();
    }

    @Override
    public UserDTO entityToDto(User entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .age(entity.getAge())
                .province(entity.getProvince())
                .city(entity.getCity())
                .role(entity.getRole())
                .build();
    }

}
