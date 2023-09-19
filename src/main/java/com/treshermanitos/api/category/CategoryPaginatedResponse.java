package com.treshermanitos.category;

import com.treshermanitos.infrastructure.config.spring.PaginatedResponseBase;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class CategoryPaginatedResponse extends PaginatedResponseBase {
    private List<CategoryDTO> categoriesDto;
}
