package com.treshermanitos.treshermanitos.product;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank
    private  String name;

    @NotBlank
    private  String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Long category;

    @NotBlank
    private  String img;

    @NotNull
    private Long stock;

    @NotNull
    private Boolean fav;
}
