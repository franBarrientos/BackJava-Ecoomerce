package com.treshermanitos.treshermanitos.role;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RoleDTOMapper implements Function<Role, RoleDTO> {
    @Override
    public RoleDTO apply(Role role) {
        return  RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
