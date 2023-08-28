package com.treshermanitos.treshermanitos.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treshermanitos.treshermanitos.config.ApiResponse;
import com.treshermanitos.treshermanitos.user.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse> getAll() {
        return ApiResponse.oK(customerService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") long id) {

        return ApiResponse.oK(customerService.getById(id));

    }

    @PostMapping()
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody CustomerRequest body) {
        return ApiResponse.oK(customerService.createOne(
                new CustomerDTO(body.getDni(),
                        body.getAddres(),
                        userService.getById(body.getUser()))));

    }

}
