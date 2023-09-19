package com.treshermanitos.product;

import com.fasterxml.jackson.annotation.JsonView;
import com.treshermanitos.config.Views;
import com.treshermanitos.category.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Data
public class ProductDTO {

    @JsonView(Views.Base.class)
    private Long id;

    @JsonView(Views.Base.class)
    private  String name;

    @JsonView(Views.Base.class)
    private  String description;

    @JsonView(Views.Base.class)
    private BigDecimal price;

    @JsonView(Views.ProductWithCategory.class)
    private CategoryDTO category;

    @JsonView(Views.Base.class)
    private  String img;

    @JsonView(Views.Base.class)
    private Long stock;

    @JsonView(Views.Base.class)
    private Boolean hasStock;

    @JsonView(Views.Base.class)
    private Boolean fav;
}
