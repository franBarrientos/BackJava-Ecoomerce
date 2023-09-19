package com.treshermanitos.auth;

import com.treshermanitos.config.JwtService;
import com.treshermanitos.role.RoleDTOMapper;
import com.treshermanitos.role.RoleRepository;
import com.treshermanitos.infrastructure.db.springdata.entities.User;
import com.treshermanitos.infrastructure.db.springdata.repository.UserRepository;
import com.treshermanitos.infrastructure.rest.spring.dto.UserDTO;
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
    private final RoleDTOMapper roleDTOMapper;

    public AuthenticationResponse register(RegisterRequest body) {
        var user = User.builder()
                .firstName(body.getFirstName())
                .lastName(body.getLastName())
                .email(body.getEmail())
                .password(passwordEncoder.encode(body.getPassword()))
                .roles(Set.of(roleRepository.findRoleByName("USER")))
                .build();
        repository.save(user);
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
        var user = repository.findByEmail(body.getEmail()).orElseThrow();
        var userDto = UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
        var jwtToken = jwtService.generateToken(user);
        return new LoginResponse(jwtToken, userDto);

    }

    public static void checkIfAdminOrSameUser(Long id, Authentication authentication) {
        Boolean hasPermissionToGetAll =
                authentication.getAuthorities().stream()
                        .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        Boolean idParamIsEqualIdUserAuthenticated = (id == ((User) authentication.getPrincipal()).getId());

        if (!hasPermissionToGetAll && !idParamIsEqualIdUserAuthenticated) {
            throw new AccessDeniedException("403");
        }
    }

}
