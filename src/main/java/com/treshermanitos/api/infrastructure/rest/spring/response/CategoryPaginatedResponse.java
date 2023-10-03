package com.treshermanitos.api.infrastructure.rest.spring.response;

import com.treshermanitos.api.application.dto.CategoryDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class CategoryPaginatedResponse extends PaginatedResponseBase {
    private List<CategoryDTO> categoriesDto;
}
