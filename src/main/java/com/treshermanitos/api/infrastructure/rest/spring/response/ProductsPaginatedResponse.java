package com.treshermanitos.api.infrastructure.rest.spring.response;

import com.treshermanitos.api.application.dto.ProductDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class ProductsPaginatedResponse extends PaginatedResponseBase {
    private List<ProductDTO> products;
}
