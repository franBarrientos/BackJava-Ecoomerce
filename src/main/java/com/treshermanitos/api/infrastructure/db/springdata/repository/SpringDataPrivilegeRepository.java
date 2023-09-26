package com.treshermanitos.api.infrastructure.db.springdata.repository;

import com.treshermanitos.api.infrastructure.db.springdata.entities.PrivilegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPrivilegeRepository extends JpaRepository<PrivilegeEntity, Long> {

    PrivilegeEntity findPrivilegeByName(String name);

}
