package com.treshermanitos.infrastructure.rest.spring.mapper;

import com.treshermanitos.domain.User;
import com.treshermanitos.infrastructure.rest.spring.dto.CustomerDetails;
import com.treshermanitos.infrastructure.rest.spring.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UserDtoMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .age(user.getAge())
                .province(user.getProvince())
                .city(user.getCity())
                .customer(user.getCustomer() != null ?
                        CustomerDetails.builder()
                                .id(user.getCustomer().getId())
                                .addres(user.getCustomer().getAddres())
                                .dni(user.getCustomer().getDni())
                                .phone(user.getCustomer().getPhone())
                                .build()
                        :
                        null
                )
                .build();

    }

}
