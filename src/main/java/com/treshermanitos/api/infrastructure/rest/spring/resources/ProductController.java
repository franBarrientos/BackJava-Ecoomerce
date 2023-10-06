package com.treshermanitos.api.infrastructure.rest.spring.resources;

import com.treshermanitos.api.application.dto.ProductAddDTO;
import com.treshermanitos.api.application.dto.ProductDTO;
import com.treshermanitos.api.application.service.ProductService;
import com.treshermanitos.api.infrastructure.config.spring.ApiResponse;
import com.treshermanitos.api.infrastructure.rest.spring.response.ProductsPaginatedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int limit,
            @RequestParam(defaultValue = "1") int category) {
        Page<ProductDTO> products = this.productService.getAll(PageRequest.of(page, limit), category);

        return ApiResponse.oK(
                ProductsPaginatedResponse.builder()
                        .products(products.getContent())
                        .totalPages(products.getTotalPages())
                        .totalItems(products.getNumberOfElements())
                        .build()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") long id) {
        return ApiResponse.oK(this.productService.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse> createOne(@ModelAttribute ProductAddDTO productAddDTO) {
        return ApiResponse.oK(this.productService.createOne(productAddDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateOne(@PathVariable(value = "id") long id,
                                                 @ModelAttribute ProductAddDTO body){
        return ApiResponse.oK(this.productService.updateById(id, body));
    }


   @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteOne(@PathVariable(value = "id") long id){
        return ApiResponse.oK( this.productService.deleteById(id));
    }

}
