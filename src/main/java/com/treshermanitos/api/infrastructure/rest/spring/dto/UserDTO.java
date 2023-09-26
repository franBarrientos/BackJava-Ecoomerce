package com.treshermanitos.api.infrastructure.rest.spring.dto;


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

    private CustomerDTOWithoutUser customer;

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
                new CustomerDTOWithoutUser(customerId,customerDni,customerAddres,customerPhone)
                :
                        null
                );
    }
}

