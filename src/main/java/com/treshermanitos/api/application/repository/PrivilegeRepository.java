package com.treshermanitos.api.application.repository;

import com.treshermanitos.api.domain.Privilege;

import java.util.Optional;

public interface PrivilegeRepository {
    Optional<Privilege> findPrivilegeByName(String name);

    Privilege save(Privilege privilege);
}
