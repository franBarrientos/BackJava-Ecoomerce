package com.treshermanitos.api.infrastructure.rest.spring.resources;

import com.treshermanitos.api.application.service.ProductService;
import com.treshermanitos.api.application.service.PurchaseService;
import com.treshermanitos.api.domain.Purchase;
import com.treshermanitos.api.infrastructure.config.spring.ApiResponse;
import com.treshermanitos.api.infrastructure.rest.spring.dto.ProductDTO;
import com.treshermanitos.api.infrastructure.rest.spring.dto.PurchaseDTO;
import com.treshermanitos.api.infrastructure.rest.spring.mapper.ProductDtoMapper;
import com.treshermanitos.api.infrastructure.rest.spring.mapper.PurchaseDtoMapper;
import com.treshermanitos.api.infrastructure.rest.spring.response.ProductsPaginatedResponse;
import com.treshermanitos.api.infrastructure.rest.spring.response.PurchasePaginatedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final PurchaseDtoMapper purchaseDtoMapper;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int limit) {
        Page<PurchaseDTO> purchases = this.purchaseService.getAllPurchases(PageRequest.of(page, limit))
                .map(purchaseDtoMapper::toDto);

        return ApiResponse.oK(
                PurchasePaginatedResponse.builder()
                        .purchases(purchases.getContent())
                        .totalPages(purchases.getTotalPages())
                        .totalItems(purchases.getNumberOfElements())
                        .build()
                );
    }


 /*   @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") long id, Authentication authentication) {
        return ApiResponse.oK(this.productService.getById(id));
    }*/


/*    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> createOne(@Valid @RequestBody ProductDTO body){
        return ApiResponse.oK(this.productService.createOne(
                this.productDtoMapper.toDomain(
                body)));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> updateOne(@PathVariable(value = "id") long id, @RequestBody ProductDTO body){
        return ApiResponse.oK(this.productService.updateById(id, body));
    }
   @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteOne(@PathVariable(value = "id") long id){
        this.productService.deleteById(id);
        return ApiResponse.oK("Product "+id+" deleted");
    }*/

}
