package com.treshermanitos.treshermanitos.user;

import java.util.function.Function;

import org.springframework.stereotype.Service;

@Service
public class UserDtoMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return  UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .age(user.getAge())
                .province(user.getProvince())
                .city(user.getCity())
                .role(user.getRole())
                .build();

    }

}
