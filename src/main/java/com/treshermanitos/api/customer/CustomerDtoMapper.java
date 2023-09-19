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
                        .id(customer.getUserEntity().getId())
                        .firstName(customer.getUserEntity().getFirstName())
                        .lastName(customer.getUserEntity().getLastName())
                        .email(customer.getUserEntity().getEmail())
                        .city(customer.getUserEntity().getCity())
                        .age(customer.getUserEntity().getAge())
                        .province(customer.getUserEntity().getProvince())
                        .build()
                )
                .build();

    }

}
