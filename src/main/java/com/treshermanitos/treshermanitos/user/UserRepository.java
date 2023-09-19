package com.treshermanitos.treshermanitos.user;

import java.util.Optional;

import com.treshermanitos.treshermanitos.user.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByIdAndStateIsTrue(Long id);

    @Query("SELECT new com.treshermanitos.treshermanitos.user.dto.UserDTO( " +
            "u.id, u.firstName, u.lastName, u.email, u.city, u.age, " +
            "u.province, u.customer.id, u.customer.dni, u.customer.addres, " +
            "u.customer.phone) FROM User u LEFT JOIN u.customer WHERE u.state = true ")
    Page<UserDTO> findAllUsersAsDtos(Pageable pageable);
}
