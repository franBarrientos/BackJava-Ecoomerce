package com.treshermanitos.api.infrastructure.rest.spring.resources;

import com.treshermanitos.api.application.service.ProductService;
import com.treshermanitos.api.infrastructure.config.spring.ApiResponse;
import com.treshermanitos.api.application.dto.ProductDTO;
import com.treshermanitos.api.application.mapper.ProductDtoMapper;
import com.treshermanitos.api.infrastructure.rest.spring.response.ProductsPaginatedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int limit) {
        Page<ProductDTO> products = this.productService.getAll(PageRequest.of(page, limit));

        return ApiResponse.oK(
                ProductsPaginatedResponse.builder()
                        .products(products.getContent())
                        .totalPages(products.getTotalPages())
                        .totalItems(products.getNumberOfElements())
                        .build()
                );
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") long id,
                                               Authentication authentication) {
        return ApiResponse.oK(this.productService.getById(id));
    }


/*    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> createOne(@Valid @RequestBody ProductDTO body){
        return ApiResponse.oK(this.productService.createOne(
                this.productDtoMapper.purchaseAddDTOtoDomain(
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
