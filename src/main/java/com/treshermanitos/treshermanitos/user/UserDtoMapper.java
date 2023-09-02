package com.treshermanitos.treshermanitos.user;

import java.util.function.Function;
import java.util.stream.Collectors;

import com.treshermanitos.treshermanitos.customer.CustomerDTO;
import com.treshermanitos.treshermanitos.customer.CustomerDtoMapper;
import com.treshermanitos.treshermanitos.role.RoleDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDtoMapper implements Function<User, UserDTO> {


    private final RoleDTOMapper roleDTOMapper;

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
                .roles(user.getRoles().stream().map(roleDTOMapper).collect(Collectors.toSet()))
                .customer(user.getCustomer() != null ?
                        CustomerDTO.builder()
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
