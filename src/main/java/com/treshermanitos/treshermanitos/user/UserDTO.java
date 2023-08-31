package com.treshermanitos.treshermanitos.user;


import com.treshermanitos.treshermanitos.customer.CustomerDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
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

    private Role role;

    private String province;

    private CustomerDTO customer;


    public UserDTO(Long id, String firstName, String lastName, String email, String city, Integer age, Role role, String province, CustomerDTO customer) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.age = age;
        this.role = role;
        this.province = province;
        this.customer = customer;
    }
}
