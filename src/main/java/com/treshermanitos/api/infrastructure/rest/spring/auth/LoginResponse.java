package com.treshermanitos.api.infrastructure.rest.spring.auth;

import com.treshermanitos.api.infrastructure.rest.spring.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String jwtToken;
    private UserDTO user;

}
