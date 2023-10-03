package com.treshermanitos.api.infrastructure.rest.spring.resources;

import com.treshermanitos.api.application.dto.AuthenticationResponse;
import com.treshermanitos.api.application.dto.LoginRequest;
import com.treshermanitos.api.application.dto.LoginResponse;
import com.treshermanitos.api.application.dto.RegisterRequest;
import com.treshermanitos.api.application.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest body) {
        return ResponseEntity.ok(authService.register(body));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> register(@RequestBody LoginRequest body) {
        return ResponseEntity.ok(authService.login(body));

    }
}
