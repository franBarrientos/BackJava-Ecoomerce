package com.treshermanitos.api.infrastructure.rest.spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.treshermanitos.api.infrastructure.rest.spring.dto.UserDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "dni is required")
    private Integer dni;

    @NotEmpty(message = "addres is required")
    private String addres;
    
    @NotEmpty(message = "phone is required")
    private String phone;

    @JsonIgnoreProperties({"customer"})
    private UserDTO user;

}

