package com.treshermanitos.treshermanitos.customer;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT NEW com.treshermanitos.treshermanitos.customer.CustomerDTO(c.id, c.dni, c.addres, c.phone, new com.treshermanitos.treshermanitos.user.UserDTO(c.user.id, c.user.firstName, c.user.lastName, c.user.email, c.user.city, c.user.age, c.user.role, c.user.province )) FROM Customer c")
    Page<CustomerDTO> findAllMapped(Pageable pageable);

    @Query("SELECT NEW com.treshermanitos.treshermanitos.customer.CustomerDTO(c.id, c.dni, c.addres, c.phone, new com.treshermanitos.treshermanitos.user.UserDTO(c.user.id, c.user.firstName, c.user.lastName, c.user.email, c.user.city, c.user.age, c.user.role, c.user.province )) FROM Customer c WHERE c.id = :id")
    Optional<CustomerDTO> findOneMapped(@Param("id") Long id);

}
