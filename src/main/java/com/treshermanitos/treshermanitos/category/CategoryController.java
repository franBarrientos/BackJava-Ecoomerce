package com.treshermanitos.treshermanitos.category;

import com.fasterxml.jackson.annotation.JsonView;
import com.treshermanitos.treshermanitos.auth.AuthService;
import com.treshermanitos.treshermanitos.config.ApiResponse;
import com.treshermanitos.treshermanitos.config.Views;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping
    @JsonView(Views.CategoryWithProduct.class) 
    public ResponseEntity<ApiResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int limit) {

        return ApiResponse.oK(this.categoryService.getAll(PageRequest.of(page, limit)));
    }

    @GetMapping("/{id}")
    @JsonView(Views.CategoryWithProduct.class) 
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") Long id, Authentication authentication) {
        //if isn't admin user or the same user authenticated, throw 403
        AuthService.checkIfAdminOrSameUser(id, authentication);
        return ApiResponse.oK(this.categoryService.getById(id));
    }
    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ApiResponse> createOne(@Valid @RequestBody CategoryDTO body) {
        //if isn't admin user or the same user authenticated, throw 403
        return ApiResponse.oK(this.categoryService.createOne(body));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @JsonView(Views.CategoryWithProduct.class)
    public ResponseEntity<ApiResponse> updateById(@PathVariable(value = "id") Long id,@RequestBody CategoryDTO body,
                                                  Authentication authentication) {
        //if isn't admin user or the same user authenticated, throw 403
        AuthService.checkIfAdminOrSameUser(id, authentication);
        return ApiResponse.oK(
                this.categoryService.updateById(id, body)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable(value = "id") Long id) {
        this.categoryService.deleteById(id);
        return ApiResponse.oK("Category "+ id + " deleted");
    }


}
