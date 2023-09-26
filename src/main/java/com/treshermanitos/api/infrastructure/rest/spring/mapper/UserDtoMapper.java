package com.treshermanitos.api.infrastructure.rest.spring.mapper;

import com.treshermanitos.api.domain.User;
import com.treshermanitos.api.infrastructure.rest.spring.dto.UserDTO;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    UserDTO toDto(User user);

    User toDomain(UserDTO userDto);
}
