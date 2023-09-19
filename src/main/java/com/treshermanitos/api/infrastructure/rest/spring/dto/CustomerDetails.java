package com.treshermanitos.infrastructure.rest.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails{
    private Long id;

    private Integer dni;

    private String addres;

    private String phone;
}

