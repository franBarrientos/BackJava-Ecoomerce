package com.treshermanitos.treshermanitos.purchase.PurchaseDto;

import com.treshermanitos.treshermanitos.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductProjection {
    private Long id;

    private  String name;

    private BigDecimal price;

    public  ProductProjection(Product p){
        this.setId(p.getId());
        this.setName(p.getName());
        this.setPrice(p.getPrice());
    }
}
