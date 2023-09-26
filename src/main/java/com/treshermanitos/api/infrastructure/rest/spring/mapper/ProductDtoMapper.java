package com.treshermanitos.api.infrastructure.rest.spring.mapper;

import com.treshermanitos.api.domain.Product;
import com.treshermanitos.api.domain.User;
import com.treshermanitos.api.infrastructure.rest.spring.dto.ProductDTO;
import com.treshermanitos.api.infrastructure.rest.spring.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDtoMapper{

    ProductDTO toDto(Product user);

    Product toDomain(ProductDTO userDto);
}
