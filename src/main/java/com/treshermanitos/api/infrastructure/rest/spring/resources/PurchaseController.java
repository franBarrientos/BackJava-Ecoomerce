package com.treshermanitos.api.infrastructure.rest.spring.resources;

import com.treshermanitos.api.application.service.PurchaseService;
import com.treshermanitos.api.infrastructure.config.spring.ApiResponse;
import com.treshermanitos.api.application.service.AuthService;
import com.treshermanitos.api.application.dto.PurchaseAddDTO;
import com.treshermanitos.api.application.dto.PurchaseDTO;
import com.treshermanitos.api.application.mapper.PurchaseAddDtoMapper;
import com.treshermanitos.api.application.mapper.PurchaseDtoMapper;
import com.treshermanitos.api.infrastructure.rest.spring.response.PurchasePaginatedResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int limit) {
        Page<PurchaseDTO> purchases = this.purchaseService
                .getAllPurchases(PageRequest.of(page, limit));

        return ApiResponse.oK(
                PurchasePaginatedResponse.builder()
                        .purchases(purchases.getContent())
                        .totalPages(purchases.getTotalPages())
                        .totalItems(purchases.getNumberOfElements())
                        .build()
        );
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") long id,
                                               Authentication authentication) {

        AuthService.checkIfAdminOrSameUser(id, authentication);

        return ApiResponse.oK(this.purchaseService.getById(id));
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> search(
            @RequestParam(value = "dni", required = false) Integer dni,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int limit,
            @Valid @RequestBody PurchaseDTO purchaseDTO) {

        Page<PurchaseDTO> purchases = this.purchaseService
                .search(dni, firstName, lastName, PageRequest.of(page, limit));

        return ApiResponse.oK(
                PurchasePaginatedResponse.builder()
                        .purchases(purchases.getContent())
                        .totalPages(purchases.getTotalPages())
                        .totalItems(purchases.getNumberOfElements())
                        .build()
        );
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<ApiResponse> createOne(@Valid @RequestBody PurchaseAddDTO body) {
        return ApiResponse.oK(this.purchaseService.createOne(body));
    }

    @GetMapping("/stadistics")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> getById() {
        return ApiResponse.oK(this.purchaseService.getStadistics());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> updateOne(@PathVariable(value = "id") long id, @RequestBody PurchaseAddDTO body){
        return ApiResponse.oK(this.purchaseService.updateById(id, body));
    }


   @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteOne(@PathVariable(value = "id") long id){
        return ApiResponse.oK(this.purchaseService.deleteById(id));
    }

}
