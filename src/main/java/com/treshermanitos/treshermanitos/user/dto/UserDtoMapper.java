package com.treshermanitos.treshermanitos.user.dto;

import java.util.function.Function;
import java.util.stream.Collectors;

import com.treshermanitos.treshermanitos.customer.CustomerDTO;
import com.treshermanitos.treshermanitos.role.RoleDTOMapper;
import com.treshermanitos.treshermanitos.user.User;
import com.treshermanitos.treshermanitos.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
