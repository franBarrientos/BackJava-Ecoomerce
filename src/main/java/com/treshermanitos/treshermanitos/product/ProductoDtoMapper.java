package com.treshermanitos.treshermanitos.product;

import com.treshermanitos.treshermanitos.category.CategoryDTO;
import com.treshermanitos.treshermanitos.category.CategoryDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ProductoDtoMapper implements Function<Product, ProductDTO> {


    @Override
    public ProductDTO apply(Product productDTO) {


        return ProductDTO.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .category(
                        CategoryDTO.builder()
                                .id(productDTO.getCategory().getId())
                                .img(productDTO.getCategory().getImg())
                                .name(productDTO.getCategory().getName())
                                .id(productDTO.getCategory().getId())
                                .state(productDTO.getCategory().getState())
                                .build()
                )
                .img(productDTO.getImg())
                .stock(productDTO.getStock())
                .hasStock(productDTO.getHasStock())
                .fav(productDTO.getFav())
                .build();

    }
}
