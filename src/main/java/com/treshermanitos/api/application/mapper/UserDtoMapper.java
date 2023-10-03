package com.treshermanitos.api.application.mapper;

import com.treshermanitos.api.domain.User;
import com.treshermanitos.api.application.dto.UserDTO;

import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    UserDTO toDto(User user);

    User toDomain(UserDTO userDto);
}
