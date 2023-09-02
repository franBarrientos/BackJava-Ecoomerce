package com.treshermanitos.treshermanitos.customer;

import com.treshermanitos.treshermanitos.user.UserDTO;
import com.treshermanitos.treshermanitos.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.treshermanitos.treshermanitos.exceptions.NotFoundException;
import com.treshermanitos.treshermanitos.exceptions.RelationshipAlreadyExist;
import com.treshermanitos.treshermanitos.user.User;
import com.treshermanitos.treshermanitos.user.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserService userService;
    private final CustomerDtoMapper customerDtoMapper;
    private final UserRepository userRepository;

    public CustomersPaginatedResponse getAll(Pageable pageable) {
        Page<CustomerDTO> data = customerRepository.findAllActiveCustomers(pageable)
                .map(customerDtoMapper);
        return CustomersPaginatedResponse.builder()
                .customers(data.getContent())
                .totalItems(data.getNumberOfElements())
                .totalPages(data.getTotalPages())
                .build();
    }


    public CustomerDTO getById(Long idLong) {
        Customer customer = customerRepository.findActiveCustomer(idLong)
                .orElseThrow(() -> new NotFoundException(" customer " + idLong + " not found"));
        return customerDtoMapper.apply(customer);
    }


    public CustomerDTO createOne(CustomerRequest body) {

        User user = userRepository.findByIdAndStateIsTrue(body.getUser())
                .orElseThrow(() -> new NotFoundException(" user " + body.getUser() + " not found"));

        if (user.getCustomer() != null) {
            throw new RelationshipAlreadyExist("can not create a relationship one to one already exist!.");
        }

        Customer customer = Customer.builder()
                .addres(body.getAddres())
                .dni(body.getDni())
                .user(user)
                .build();

        return customerDtoMapper.apply(customerRepository.save(customer));
    }

    public CustomerDTO updateById(Long id, CustomerDTO body) {
        Customer customer = this.customerRepository.findActiveCustomer(id)
                .orElseThrow(() -> new NotFoundException("customer " + id + " not found "));

        if (body.getDni() != null) {
            customer.setDni(body.getDni());
        }


        if (body.getAddres() != null) {
            customer.setAddres(body.getAddres());
        }


        if (body.getPhone() != null) {
            customer.setPhone(body.getPhone());
        }

        return this.customerDtoMapper
                .apply(this.customerRepository.save(customer));

    }


}
