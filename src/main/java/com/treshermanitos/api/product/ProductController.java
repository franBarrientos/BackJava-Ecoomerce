package com.treshermanitos.api.product;

import com.fasterxml.jackson.annotation.JsonView;
import com.treshermanitos.infrastructure.config.spring.ApiResponse;
import com.treshermanitos.infrastructure.config.spring.Views;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @JsonView(Views.ProductWithCategory.class)
    public ResponseEntity<ApiResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int limit) {
        return ApiResponse.oK(this.productService.getAll(PageRequest.of(page, limit)));
    }


    @GetMapping("/{id}")
    @JsonView(Views.ProductWithCategory.class)
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") long id, Authentication authentication) {
        return ApiResponse.oK(this.productService.getById(id));
    }


    @PostMapping
    @JsonView(Views.ProductWithCategory.class)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> createOne(@Valid @RequestBody ProductRequest body){
        return ApiResponse.oK(this.productService.createOne(body));
    }
    @PutMapping("/{id}")
    @JsonView(Views.ProductWithCategory.class)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> updateOne(@PathVariable(value = "id") long id, @RequestBody ProductDTO body){
        return ApiResponse.oK(this.productService.updateById(id, body));
    }
   @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteOne(@PathVariable(value = "id") long id){
        this.productService.deleteById(id);
        return ApiResponse.oK("Product "+id+" deleted");
    }

}
