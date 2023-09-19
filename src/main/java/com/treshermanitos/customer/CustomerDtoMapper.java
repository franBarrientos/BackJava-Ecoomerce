package com.treshermanitos.customer;

import java.util.function.Function;

import com.treshermanitos.infrastructure.rest.spring.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDtoMapper implements Function<Customer, CustomerDTO> {
    @Override
    public CustomerDTO apply(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .addres(customer.getAddres())
                .dni(customer.getDni())
                .phone(customer.getPhone())
                .user(UserDTO.builder()
                        .id(customer.getUser().getId())
                        .firstName(customer.getUser().getFirstName())
                        .lastName(customer.getUser().getLastName())
                        .email(customer.getUser().getEmail())
                        .city(customer.getUser().getCity())
                        .age(customer.getUser().getAge())
                        .province(customer.getUser().getProvince())
                        .build()
                )
                .build();

    }

}
