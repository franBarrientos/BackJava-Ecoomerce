package com.treshermanitos.product;

import com.treshermanitos.config.PaginatedResponseBase;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class ProductsPaginatedResponse extends PaginatedResponseBase {
    private List<ProductDTO> products;
}
