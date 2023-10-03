package com.treshermanitos.api.infrastructure.rest.spring.resources;

import com.treshermanitos.api.application.service.CustomerService;
import com.treshermanitos.api.infrastructure.config.spring.ApiResponse;
import com.treshermanitos.api.application.service.AuthService;
import com.treshermanitos.api.application.dto.CustomerDTO;
import com.treshermanitos.api.application.mapper.CustomerDtoMapper;
import com.treshermanitos.api.infrastructure.rest.spring.response.CustomersPaginatedResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int limit) {

        Page<CustomerDTO> customerDTOPage = this.customerService.getAll(PageRequest.of(page, limit));

        return ApiResponse.oK(
                CustomersPaginatedResponse.builder()
                        .customers(customerDTOPage.getContent())
                        .totalItems(customerDTOPage.getNumberOfElements())
                        .totalPages(customerDTOPage.getTotalPages())
                        .build()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") long id, Authentication authentication) {
        // if isn't admin and doesn't have the same id throw 403
        AuthService.checkIfAdminOrSameUser(id, authentication);
        return ApiResponse.oK(this.customerService.getById(id));
    }

    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody CustomerDTO body) {
        return ApiResponse.oK(this.customerService.createOne(body));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse> update(@PathVariable(value = "id") Long id,
                                              @RequestBody CustomerDTO body,
                                              Authentication authentication) {
        // if isn't admin and doesn't have the same id throw 403
        AuthService.checkIfAdminOrSameUser(id, authentication);
        return ApiResponse.oK(this.customerService.updateById(id, body));
    }
}
