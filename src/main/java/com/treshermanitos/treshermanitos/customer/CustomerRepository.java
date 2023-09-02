package com.treshermanitos.treshermanitos.customer;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.user.state = true")
    Page<Customer> findAllActiveCustomers(Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.user.state = true AND c.id = :id")
    Optional<Customer> findActiveCustomer(@Param("id") Long id);

}
