package com.treshermanitos.treshermanitos.user;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByStateIsTrue(Pageable pageable);


    Optional<User> findByEmail(String email);

    Optional<User> findByIdAndStateIsTrue(Long id);

}
