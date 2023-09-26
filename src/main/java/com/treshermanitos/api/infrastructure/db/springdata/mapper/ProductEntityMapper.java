package com.treshermanitos.api.infrastructure.db.springdata.mapper;

import com.treshermanitos.api.domain.Product;
import com.treshermanitos.api.infrastructure.db.springdata.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

    @Mapping(target = "category.products", ignore = true)
    Product toDomain(ProductEntity entity);


    @Mapping(target = "category.products", ignore = true)
    ProductEntity toEntity(Product domain);
}

