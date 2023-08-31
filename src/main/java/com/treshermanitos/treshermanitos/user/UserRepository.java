package com.treshermanitos.treshermanitos.user;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT NEW com.treshermanitos.treshermanitos.user.UserDTO("
            + "c.id, c.firstName, c.lastName, c.email, c.city, c.age, c.role, c.province,"
            + "NEW com.treshermanitos.treshermanitos.customer.CustomerDTO(c.customer.id, c.customer.dni, c.customer.addres, c.customer.phone))"
            + " FROM User c WHERE c.state = true ")
    public Page<UserDTO> findAllMapped(Pageable pageable);



    @Query("SELECT NEW com.treshermanitos.treshermanitos.user.UserDTO("
            + "c.id, c.firstName, c.lastName, c.email, c.city, c.age, c.role, c.province,"
            + "NEW com.treshermanitos.treshermanitos.customer.CustomerDTO(c.customer.id, c.customer.dni, c.customer.addres, c.customer.phone))"
            + " FROM User c WHERE c.id = :id ")
    public Optional<UserDTO> findByIdMapped(@Param("id") Long id);



    public Optional<User> findByEmail(String email);

}
