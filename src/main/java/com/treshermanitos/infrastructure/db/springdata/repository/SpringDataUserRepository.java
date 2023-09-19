package com.treshermanitos.infrastructure.db.springdata.repository;

import com.treshermanitos.infrastructure.db.springdata.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
