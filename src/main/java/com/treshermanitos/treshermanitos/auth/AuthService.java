package com.treshermanitos.treshermanitos.auth;

import com.treshermanitos.treshermanitos.role.RoleDTOMapper;
import com.treshermanitos.treshermanitos.role.RoleRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.treshermanitos.treshermanitos.config.JwtService;
import com.treshermanitos.treshermanitos.user.User;
import com.treshermanitos.treshermanitos.user.dto.UserDTO;
import com.treshermanitos.treshermanitos.user.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

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
