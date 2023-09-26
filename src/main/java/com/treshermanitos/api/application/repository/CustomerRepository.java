package com.treshermanitos.api.application.repository;

import com.treshermanitos.api.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerRepository {

    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findAllActiveCustomers(Pageable pageable);

    Optional<Customer> findById(Long id);

    Optional<Customer> findActiveCustomer(Long id);

    Customer save(Customer customer);


}
