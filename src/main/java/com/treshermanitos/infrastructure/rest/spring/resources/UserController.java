package com.treshermanitos.infrastructure.rest.spring.resources;

import com.treshermanitos.application.service.UserService;
import com.treshermanitos.config.ApiResponse;
import com.treshermanitos.infrastructure.rest.spring.dto.UserDTO;
import com.treshermanitos.infrastructure.rest.spring.mapper.UserDtoMapper;
import com.treshermanitos.infrastructure.rest.spring.response.UsersPaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.treshermanitos.auth.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "15") int limit) {
        Page<UserDTO> userDTOS = this.userService.getAllUsers(PageRequest.of(page, limit))
                .map(userDtoMapper::apply);

        return ApiResponse.oK(
                UsersPaginatedResponse.builder()
                        .users(userDTOS.getContent())
                        .totalItems(userDTOS.getNumberOfElements())
                        .totalPages(userDTOS.getTotalPages())
                        .build()
        );
    }

 /*   @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") long id, Authentication authentication) {
        // if isn't admin and doesn't have the same id throw 403
        AuthService.checkIfAdminOrSameUser(id, authentication);
        return ApiResponse.oK(userService.getById(id));

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse> update(@PathVariable(value = "id") long id, @RequestBody UserDTO body, Authentication authentication) {
        // if isn't admin and doesn't have the same id throw 403
        AuthService.checkIfAdminOrSameUser(id, authentication);
        return ApiResponse.oK(userService.updateById(id, body));

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> delete(@PathVariable(value = "id") long id) {
        userService.deleteById(id);
        return ApiResponse.oK("user " + id + " deleted");
    }*/

}
