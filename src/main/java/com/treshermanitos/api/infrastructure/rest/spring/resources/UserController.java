package com.treshermanitos.api.infrastructure.rest.spring.resources;

import com.treshermanitos.api.application.service.UserService;
import com.treshermanitos.api.infrastructure.config.spring.ApiResponse;
import com.treshermanitos.api.application.service.AuthService;
import com.treshermanitos.api.application.dto.UserDTO;
import com.treshermanitos.api.application.mapper.UserDtoMapper;
import com.treshermanitos.api.infrastructure.rest.spring.response.UsersPaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int limit) {

        Page<UserDTO> userDTOS = this.userService.getAllUsers(PageRequest.of(page, limit));

        return ApiResponse.oK(
                UsersPaginatedResponse.builder()
                        .users(userDTOS.getContent())
                        .totalItems(userDTOS.getNumberOfElements())
                        .totalPages(userDTOS.getTotalPages())
                        .build()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") long id, Authentication authentication) {
        // if isn't admin and doesn't have the same id throw 403
        AuthService.checkIfAdminOrSameUser(id, authentication);
        return ApiResponse.oK(this.userService.getUserIsActive(id));

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse> update(@PathVariable(value = "id") long id, @RequestBody UserDTO body, Authentication authentication) {
        // if isn't admin and doesn't have the same id throw 403
        AuthService.checkIfAdminOrSameUser(id, authentication);
        return ApiResponse.oK(this.userService.updateById(id, body));

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> delete(@PathVariable(value = "id") long id) {
        return ApiResponse.oK(this.userService.deleteById(id));
    }

}
