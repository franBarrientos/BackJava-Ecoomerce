package com.treshermanitos.api.infrastructure.db.springdata.mapper;

import com.treshermanitos.api.domain.Category;
import com.treshermanitos.api.domain.Customer;
import com.treshermanitos.api.infrastructure.db.springdata.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {


    Category toDomain(CategoryEntity customerEntity);


    CategoryEntity toEntity(Category customer);
}
