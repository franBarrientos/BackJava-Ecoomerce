package com.treshermanitos.treshermanitos.auth;

import com.treshermanitos.treshermanitos.user.UserDTO;

public record LoginResponse(String jwtToken, UserDTO user) {
    
}
