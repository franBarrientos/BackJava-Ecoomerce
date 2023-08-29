package com.treshermanitos.treshermanitos.user;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.treshermanitos.treshermanitos.auth.AuthService;
import com.treshermanitos.treshermanitos.config.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "15") int limit) {
        return ApiResponse.oK(userService.getAllEntities(PageRequest.of(page, limit)));
    }

    @GetMapping("/{id}")
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
    }

}
