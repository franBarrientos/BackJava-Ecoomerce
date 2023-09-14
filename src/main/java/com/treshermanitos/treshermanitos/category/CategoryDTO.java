package com.treshermanitos.treshermanitos.category;

import com.fasterxml.jackson.annotation.JsonView;
import com.treshermanitos.treshermanitos.config.Views;
import com.treshermanitos.treshermanitos.product.Product;
import com.treshermanitos.treshermanitos.product.ProductDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    @JsonView(Views.Base.class)
    private Long id;

    @NotEmpty
    @JsonView(Views.Base.class)
    private String name;

    @NotEmpty
    @JsonView(Views.Base.class)
    private String img;

    @JsonView(Views.CategoryWithProduct.class)
    private Set<ProductDTO> products;

    @JsonView(Views.Base.class)
    private Boolean state;

}
