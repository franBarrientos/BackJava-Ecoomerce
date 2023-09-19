package com.treshermanitos.treshermanitos.purchase;

import com.treshermanitos.treshermanitos.auth.AuthService;
import com.treshermanitos.treshermanitos.config.ApiResponse;
import com.treshermanitos.treshermanitos.purchase.dto.PurchaseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer limit) {
        return ApiResponse.oK(this.purchaseService.getAll(PageRequest.of(page, limit)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/customer")
    public ResponseEntity<ApiResponse> getAllByCustomerDetails(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer limit,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") Integer dni) {
        return ApiResponse.oK(this.purchaseService.getAllByCustomerDetails(PageRequest.of(page, limit),name, dni));
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") Long id, Authentication authentication) {
        // if isn't admin and doesn't have the same id throw 403
        AuthService.checkIfAdminOrSameUser(id, authentication);
        return ApiResponse.oK(this.purchaseService.getById(id));

    }
    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<ApiResponse> createOne(@Valid @RequestBody PurchaseDTO body) {
        return ApiResponse.oK(this.purchaseService.createOne(body));
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ApiResponse> update(@PathVariable(value = "id") Long id, @RequestBody PurchaseDTO body,
                                              Authentication authentication) {
        // if isn't admin and doesn't have the same id throw 403
        AuthService.checkIfAdminOrSameUser(id, authentication);
        return ApiResponse.oK(this.purchaseService.updateById(id, body));

    }


}
