package com.treshermanitos.api.infrastructure.db.springdata.repository;

import com.treshermanitos.api.application.repository.CustomerRepository;
import com.treshermanitos.api.domain.Customer;
import com.treshermanitos.api.infrastructure.db.springdata.entities.CustomerEntity;
import com.treshermanitos.api.infrastructure.db.springdata.mapper.CustomerEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerDboRepository implements CustomerRepository {

    private final SpringDataCustomerRepository customerRepository;
    private final CustomerEntityMapper customerEntityMapper;

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return this.customerRepository
                .findAll(pageable)
                .map(customerEntityMapper::toDomain);
    }

    @Override
    public Page<Customer> findAllActiveCustomers(Pageable pageable) {
        return this.customerRepository
                .findAllActiveCustomers(pageable)
                .map(customerEntityMapper::toDomain);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        Optional<CustomerEntity> customerEntity = this.customerRepository.findById(id);
        return customerEntity.isPresent()?
                Optional.of(
                        this.customerEntityMapper.toDomain(customerEntity.get()))
                :
            Optional.empty();
    }

    @Override
    public Optional<Customer> findActiveCustomer(Long id) {
        Optional<CustomerEntity> customerEntity = this.customerRepository.findActiveCustomer(id);
        return customerEntity.isPresent()?
                Optional.of(
                        this.customerEntityMapper.toDomain(customerEntity.get()))
                :
                Optional.empty();
    }

    @Override
    public Customer save(Customer customer) {
        return this.customerEntityMapper.toDomain(
                this.customerRepository.save(this.customerEntityMapper.toEntity(customer)));
    }

    
}
