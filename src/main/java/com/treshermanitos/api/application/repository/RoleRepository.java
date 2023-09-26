package com.treshermanitos.api.application.repository;

import com.treshermanitos.api.domain.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findRoleByName(String name);
    Role save(Role product);
}
