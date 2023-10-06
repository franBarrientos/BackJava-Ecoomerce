package com.treshermanitos.api.infrastructure.rest.spring.resources;

import com.treshermanitos.api.application.dto.AuthenticationResponse;
import com.treshermanitos.api.application.dto.LoginRequest;
import com.treshermanitos.api.application.dto.LoginResponse;
import com.treshermanitos.api.application.dto.RegisterRequest;
import com.treshermanitos.api.application.service.AuthService;
import com.treshermanitos.api.infrastructure.config.spring.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest body) {
        return ApiResponse.oK(authService.register(body));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest body) {
        return ApiResponse.oK(authService.login(body));

    }
    @PostMapping("/google")
    public ResponseEntity<ApiResponse> loginGoogle(@RequestBody Map body) {
        return ApiResponse.oK(authService.loginGoogle(body));

    }
}
