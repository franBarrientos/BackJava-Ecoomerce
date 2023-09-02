package com.treshermanitos.treshermanitos.customer;

import java.util.function.Function;
import java.util.stream.Collectors;

import com.treshermanitos.treshermanitos.role.RoleDTOMapper;
import com.treshermanitos.treshermanitos.user.User;
import com.treshermanitos.treshermanitos.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.treshermanitos.treshermanitos.user.UserDtoMapper;

import lombok.AllArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerDtoMapper implements Function<Customer, CustomerDTO> {


    private final RoleDTOMapper roleDTOMapper;


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
                        .roles(customer.getUser().getRoles() != null
                                ?
                                customer.getUser().getRoles().stream().map(roleDTOMapper).collect(Collectors.toSet())
                                :
                                null
                                )
                        .province(customer.getUser().getProvince())
                        .build()
                )
                .build();

    }

}
