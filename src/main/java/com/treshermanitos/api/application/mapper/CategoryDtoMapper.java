package com.treshermanitos.api.application.mapper;

import com.treshermanitos.api.domain.Category;
import com.treshermanitos.api.application.dto.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryDtoMapper {
    CategoryDTO toDto(Category category);

    Category toDomain(CategoryDTO categoryDTO);
}
