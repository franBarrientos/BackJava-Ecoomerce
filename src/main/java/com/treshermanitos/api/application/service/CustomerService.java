package com.treshermanitos.api.application.service;

import com.treshermanitos.api.application.exceptions.NotFoundException;
import com.treshermanitos.api.application.exceptions.RelationshipAlreadyExist;
import com.treshermanitos.api.application.repository.CustomerRepository;
import com.treshermanitos.api.application.repository.UserRepository;
import com.treshermanitos.api.domain.Customer;
import com.treshermanitos.api.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;



    public Page<Customer> getAll(Pageable pageable){
        return this.customerRepository.findAllActiveCustomers(pageable);
    }

    public Customer getById(Long id){
        return this.customerRepository
                .findActiveCustomer(id)
                .orElseThrow(() -> new NotFoundException(" customer " + id + " not found"));
    }

    public Customer createOne(Customer customer){
        User user = this.userRepository.findUserIsActive(customer.getUser().getId())
                .orElseThrow(() -> new NotFoundException(" user " + customer.getUser() + " not found"));

        if (user.getCustomer() != null) {
            throw new RelationshipAlreadyExist("can not create a relationship one to one already exist!.");
        }

        return this.customerRepository.save(customer);
    }

    public Customer updateById(Long id, Customer customer){
        Customer customerToUpdate = this.customerRepository.findActiveCustomer(id)
                .orElseThrow(() -> new NotFoundException("customer " + id + " not found "));

        if (customer.getDni() != null) {
            customer.setDni(customer.getDni());
        }


        if (customer.getAddres() != null) {
            customer.setAddres(customer.getAddres());
        }


        if (customer.getPhone() != null) {
            customer.setPhone(customer.getPhone());
        }

        return this.customerRepository.save(customer);

    }



}
