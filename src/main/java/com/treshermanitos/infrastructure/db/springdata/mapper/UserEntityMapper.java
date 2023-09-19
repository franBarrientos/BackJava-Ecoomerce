package com.treshermanitos.infrastructure.db.springdata.mapper;

import java.util.function.Function;


import com.treshermanitos.domain.Customer;
import com.treshermanitos.domain.User;
import com.treshermanitos.infrastructure.rest.spring.dto.CustomerDetails;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEntityMapper implements Function<com.treshermanitos.infrastructure.db.springdata.entities.User, User> {

    @Override
    public User apply(com.treshermanitos.infrastructure.db.springdata.entities.User user) {
        return new User(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getCity(),
                user.getAge(),
                null,
                user.getProvince(),
                user.getState(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getCustomer() != null ?
                        new Customer(user.getCustomer().getId(), user.getCustomer().getDni(),
                                user.getCustomer().getAddres(), user.getCustomer().getPhone(),
                                null, null, user.getCustomer().getCreatedAt(),
                                user.getCustomer().getUpdatedAt())
                        :
                        null

        );
    }


}
