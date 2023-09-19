package com.treshermanitos.treshermanitos.purchase.purchasesProducts;

import com.treshermanitos.treshermanitos.product.Product;
import com.treshermanitos.treshermanitos.purchase.Purchase;
import com.treshermanitos.treshermanitos.purchase.dto.ProductSold;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "`purchases_products`")
public class PurchaseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchaseId")
    private Purchase purchase;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    private Product product;

    private Integer quantity;

    private BigDecimal totalPrice;

    private Date createdAt;

    private Date updatedAt;



}
