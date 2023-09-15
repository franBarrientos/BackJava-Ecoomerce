package com.treshermanitos.treshermanitos.purchase;

import com.treshermanitos.treshermanitos.auth.AuthService;
import com.treshermanitos.treshermanitos.config.ApiResponse;
import com.treshermanitos.treshermanitos.purchase.PurchaseDto.PurchaseDTO;
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
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int limit) {
        return ApiResponse.oK(this.purchaseService.getAll(PageRequest.of(page, limit)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/definitive")
    public ResponseEntity<ApiResponse> getAllDefinitive(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int limit) {
        return ApiResponse.oK(this.purchaseService.getAllDefinitive(PageRequest.of(page, limit)));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllClosedProjection")
    public ResponseEntity<ApiResponse> getAllClosedProjection(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int limit) {
        return ApiResponse.oK(this.purchaseService.getAllClosedProjection(PageRequest.of(page, limit)));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllClosedProjectionRepeat")
    public ResponseEntity<ApiResponse> getAllClosedProjectionRepeat(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int limit) {
        return ApiResponse.oK(this.purchaseService.getAllClosedProjectionRepeat(PageRequest.of(page, limit)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllClosedProjectionFaster")
    public ResponseEntity<ApiResponse> getAllClosedProjectionFaster(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int limit) {
        return ApiResponse.oK(this.purchaseService.getAllClosedProjectionFaster(PageRequest.of(page, limit)));
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") long id, Authentication authentication) {
        // if isn't admin and doesn't have the same id throw 403
        AuthService.checkIfAdminOrSameUser(id, authentication);
        return ApiResponse.oK(this.purchaseService.getById(id));

    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ApiResponse> update(@PathVariable(value = "id") long id, @RequestBody PurchaseDTO body,
                                              Authentication authentication) {
        // if isn't admin and doesn't have the same id throw 403
        AuthService.checkIfAdminOrSameUser(id, authentication);
        return ApiResponse.oK(this.purchaseService.updateById(id, body));

    }


}
