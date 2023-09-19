package com.treshermanitos.treshermanitos.user.dto;


import com.treshermanitos.treshermanitos.customer.CustomerDTO;
import com.treshermanitos.treshermanitos.role.Role;
import com.treshermanitos.treshermanitos.role.RoleDTO;
import com.treshermanitos.treshermanitos.role.RoleDTOMapper;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {


    private Long id;
    @NotBlank(message = "First name is required")
    private String firstName;

    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    private String city;

    private Integer age;

    private String province;

    private CustomerDetails customer;

    public UserDTO(Long id, String firstName, String lastName,
                   String email, String city,
                   Integer age, String province,
                   Long customerId, Integer customerDni,
                   String customerAddres, String customerPhone) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setCity(city);
        this.setAge(age);
        this.setProvince(province);
        this.setCustomer(
                customerId != null ?
                new CustomerDetails(customerId,customerDni,customerAddres,customerPhone)
                :
                        null
                );
    }
}

