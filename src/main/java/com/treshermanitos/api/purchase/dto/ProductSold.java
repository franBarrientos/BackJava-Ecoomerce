package com.treshermanitos.api.purchase.dto;

import com.treshermanitos.api.purchase.purchasesProducts.PurchaseProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductSold {
    private Long id;

    private  String name;

    private Integer quantity;

    private BigDecimal totalPrice;

    private BigDecimal price;


    public ProductSold(PurchaseProduct pp){
        this.setId(pp.getProduct().getId());
        this.setName(pp.getProduct().getName());
        this.setQuantity(pp.getQuantity());
        this.setPrice(pp.getProduct().getPrice());
        this.setTotalPrice(pp.getTotalPrice());
    }

}
