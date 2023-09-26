package com.treshermanitos.api.infrastructure.db.springdata.repository;

import com.treshermanitos.api.infrastructure.db.springdata.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByIdAndStateIsTrue(Long id);

}
