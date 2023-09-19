package com.treshermanitos.application.repository;

import com.treshermanitos.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository {
    Page<User> findAll(Pageable pageable);

    Optional<User> findById(Long id);

    User findByEmail(String email);

    User save (User user);

    Optional<User> findUserIsActive(Long id);

    boolean deleteById(Long id);




}
