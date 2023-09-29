package com.treshermanitos.api.infrastructure.db.springdata.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "PurchaseProduct")
@Table(name = "`purchases_products`")
public class PurchaseProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchaseId")
    private PurchaseEntity purchase;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity product;

    private Integer quantity;

    private BigDecimal totalPrice;
}
