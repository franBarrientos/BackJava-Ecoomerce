package com.treshermanitos.api.infrastructure.rest.spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Data
public class ProductDTO {

    private Long id;

    private  String name;

    private  String description;

    private BigDecimal price;

    @JsonIgnoreProperties({"products"})
    private CategoryDTO category;

    private  String img;

    private Long stock;

    private Boolean hasStock;

    private Boolean fav;
}
