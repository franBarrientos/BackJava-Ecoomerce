package com.treshermanitos.api.infrastructure.rest.spring.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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

    @JsonIgnoreProperties({"user"})
    private CustomerDTO customer;

}

