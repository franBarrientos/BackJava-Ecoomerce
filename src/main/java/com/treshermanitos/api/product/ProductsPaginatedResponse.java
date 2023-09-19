package com.treshermanitos.api.product;

import com.treshermanitos.infrastructure.config.spring.PaginatedResponseBase;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class ProductsPaginatedResponse extends PaginatedResponseBase {
    private List<ProductDTO> products;
}
