package com.treshermanitos.api.domain;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseProduct {
    private Long id;

    private Purchase purchase;

    private Product product;

    private Integer quantity;

    private BigDecimal totalPrice;

}
