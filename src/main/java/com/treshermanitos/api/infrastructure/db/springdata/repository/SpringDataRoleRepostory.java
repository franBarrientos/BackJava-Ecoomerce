package com.treshermanitos.api.infrastructure.db.springdata.repository;

import com.treshermanitos.api.infrastructure.db.springdata.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRoleRepostory extends JpaRepository<RoleEntity, Long> {
    RoleEntity findRoleByName(String name);
}
