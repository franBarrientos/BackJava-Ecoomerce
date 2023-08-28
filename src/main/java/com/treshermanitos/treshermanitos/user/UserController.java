package com.treshermanitos.treshermanitos.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treshermanitos.treshermanitos.config.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        return ApiResponse.oK(userService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") long id) {
        return ApiResponse.oK(userService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable(value = "id") long id, @RequestBody UserDTO body) {
        return ApiResponse.oK(userService.updateById(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable(value = "id") long id){
        userService.deleteById(id);
        return ApiResponse.oK("user "+id + " deleted");
    }

}
