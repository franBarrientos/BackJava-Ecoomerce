package com.treshermanitos.treshermanitos.user;


import com.treshermanitos.treshermanitos.customer.CustomerDTO;
import com.treshermanitos.treshermanitos.role.Role;
import com.treshermanitos.treshermanitos.role.RoleDTO;
import com.treshermanitos.treshermanitos.role.RoleDTOMapper;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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

    private Set<RoleDTO> roles;

    private String province;

    private CustomerDTO customer;





}
