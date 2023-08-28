package com.treshermanitos.treshermanitos.customer;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.treshermanitos.treshermanitos.user.UserDtoMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerDtoMapper implements Function<Customer, CustomerDTO> {

    private final UserDtoMapper userDtoMapper;

    @Override
    public CustomerDTO apply(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .addres(customer.getAddres())
                .dni(customer.getDni())
                .phone(customer.getPhone())
                .user(userDtoMapper.apply(customer.getUser()))
                .build();

    }

}
