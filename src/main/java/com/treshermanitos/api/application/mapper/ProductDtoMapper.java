package com.treshermanitos.api.application.mapper;

import com.treshermanitos.api.domain.Product;
import com.treshermanitos.api.application.dto.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDtoMapper{

    ProductDTO toDto(Product user);

    Product toDomain(ProductDTO userDto);
}
