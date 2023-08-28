package com.treshermanitos.treshermanitos.customer;

import com.treshermanitos.treshermanitos.user.UserDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long id;

    @NotBlank(message = "dni is required")
    private Integer dni;

    @NotBlank(message = "addres is required")
    private String addres;
    
    @NotBlank(message = "phone is required")
    private String phone;

    private UserDTO user;

    public CustomerDTO(Integer dni, String addres, UserDTO user) {
        setDni(dni);
        setAddres(addres);
        setUser(user);
    }

}
