package com.treshermanitos.api.infrastructure.rest.spring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.treshermanitos.api.infrastructure.rest.spring.dto.UserDTO;

import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "dni is required")
    private Integer dni;

    @NotEmpty(message = "addres is required")
    private String addres;
    
    @NotEmpty(message = "phone is required")
    private String phone;

    private UserDTO user;

    public CustomerDTO(Integer dni, String addres, UserDTO user) {
        this.setDni(dni);
        this.setAddres(addres);
        this.setUser(user);
    }
    public CustomerDTO(Integer dni, String addres, String phone) {
        this.setDni(dni);
        this.setAddres(addres);
        this.setPhone(phone);
    }
 public CustomerDTO(Long id, Integer dni, String addres, String phone) {
        this.setId(id);
        this.setDni(dni);
        this.setAddres(addres);
        this.setPhone(phone);
    }

}

