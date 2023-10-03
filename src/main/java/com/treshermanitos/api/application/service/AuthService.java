package com.treshermanitos.api.application.service;

import com.treshermanitos.api.application.exceptions.NotFoundException;
import com.treshermanitos.api.application.mapper.CustomerDtoMapper;
import com.treshermanitos.api.application.repository.RoleRepository;
import com.treshermanitos.api.application.repository.UserRepository;
import com.treshermanitos.api.infrastructure.config.spring.CustomUserDetails;
import com.treshermanitos.api.infrastructure.config.spring.JwtService;
import com.treshermanitos.api.infrastructure.db.springdata.entities.UserEntity;
import com.treshermanitos.api.infrastructure.db.springdata.mapper.CustomerEntityMapper;
import com.treshermanitos.api.infrastructure.db.springdata.mapper.RoleEntityMapper;
import com.treshermanitos.api.infrastructure.db.springdata.mapper.UserEntityMapper;
import com.treshermanitos.api.application.dto.UserDTO;
import com.treshermanitos.api.application.dto.AuthenticationResponse;
import com.treshermanitos.api.application.dto.LoginRequest;
import com.treshermanitos.api.application.dto.LoginResponse;
import com.treshermanitos.api.application.dto.RegisterRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;
    private final UserEntityMapper userEntityMapper;
    private final CustomerDtoMapper customerDtoMapper;
    private final CustomerEntityMapper customerEntityMapper;

    public AuthenticationResponse register(RegisterRequest body) {
        var user = UserEntity.builder()
                .firstName(body.getFirstName())
                .lastName(body.getLastName())
                .email(body.getEmail())
                .password(passwordEncoder.encode(body.getPassword()))
                .roles(Set.of(
                        this.roleEntityMapper
                                .toEntity(roleRepository
                                        .findRoleByName("USER")
                                        .orElseThrow(()-> new NotFoundException("Role not found")))))
                .build();
        repository.save(userEntityMapper.toDomain(user));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public LoginResponse login(LoginRequest body) {
        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(body.getEmail(),
                                body.getPassword()));
        var user = this.userEntityMapper.toEntity(
                repository.findByEmail(body.getEmail()).orElseThrow());
        var userDto = UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .customer(this.customerDtoMapper.toDto
                        (this.customerEntityMapper.toDomain(user.getCustomer())))
                .build();
        var jwtToken = jwtService.generateToken(user);
        return new LoginResponse(jwtToken, userDto);

    }

    public static void checkIfAdminOrSameUser(Long id, Authentication authentication) {
        Boolean hasPermissionToGetAll =
                authentication.getAuthorities().stream()
                        .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        Boolean idParamIsEqualIdUserAuthenticated =
                (id == ((CustomUserDetails) authentication.getPrincipal()).getUser().getId());

        if (!hasPermissionToGetAll && !idParamIsEqualIdUserAuthenticated) {
            throw new AccessDeniedException("403");
        }
    }

}
