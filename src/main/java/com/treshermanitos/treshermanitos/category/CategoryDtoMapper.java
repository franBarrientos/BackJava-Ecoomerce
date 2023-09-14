package com.treshermanitos.treshermanitos.category;

import com.treshermanitos.treshermanitos.product.ProductDTO;
import com.treshermanitos.treshermanitos.product.ProductoDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryDtoMapper implements Function<Category, CategoryDTO> {

    @Override
    public CategoryDTO apply(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .img(category.getImg())
                .name(category.getName())
                .id(category.getId())
                .state(category.getState())
                .products(category.getProducts().stream()
                        .map((product) -> ProductDTO.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .description(product.getDescription())
                                .price(product.getPrice())
                                .img(product.getImg())
                                .stock(product.getStock())
                                .hasStock(product.getHasStock())
                                .fav(product.getFav())
                                .build()
                        ).collect(Collectors.toSet()))
                .build();
    }
}
