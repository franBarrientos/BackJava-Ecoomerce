package com.treshermanitos.api.infrastructure.rest.spring.resources;

import com.treshermanitos.api.application.service.CategoryService;
import com.treshermanitos.api.infrastructure.config.spring.ApiResponse;
import com.treshermanitos.api.application.service.AuthService;
import com.treshermanitos.api.application.dto.CategoryDTO;
import com.treshermanitos.api.application.mapper.CategoryDtoMapper;
import com.treshermanitos.api.infrastructure.rest.spring.response.CategoryPaginatedResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<ApiResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int limit) {

        Page<CategoryDTO> categoryDTOPage =
                this.categoryService.getAll(PageRequest.of(page, limit));

        return ApiResponse.oK(
                CategoryPaginatedResponse.builder()
                        .categoriesDto(categoryDTOPage.getContent())
                        .totalItems(categoryDTOPage.getNumberOfElements())
                        .totalPages(categoryDTOPage.getTotalPages())
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") Long id,
                                               Authentication authentication) {
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
    public ResponseEntity<ApiResponse> updateById(@PathVariable(value = "id") Long id,
                                                  @RequestBody CategoryDTO body,
                                                  Authentication authentication) {

        //if isn't admin user or the same user authenticated, throw 403
        AuthService.checkIfAdminOrSameUser(id, authentication);
        return ApiResponse.oK(this.categoryService.updateById(id, body));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable(value = "id") Long id) {
        return ApiResponse.oK(this.categoryService.deleteById(id));
    }


}
