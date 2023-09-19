package com.treshermanitos.treshermanitos.auth;

import com.treshermanitos.treshermanitos.user.dto.UserDTO;

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
