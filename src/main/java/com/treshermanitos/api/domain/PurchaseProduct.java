package com.treshermanitos.api.domain;
import java.math.BigDecimal;

public class PurchaseProduct {
    private Long id;

    private Purchase purchase;

    private Product product;

    private Integer quantity;

    private BigDecimal totalPrice;

    public PurchaseProduct(Long id, Purchase purchase, Product product, Integer quantity, BigDecimal totalPrice) {
        this.id = id;
        this.purchase = purchase;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long p_id) {
        this.id = p_id;
    }

    public Purchase getPurchase() {
        return this.purchase;
    }

    public void setPurchase(Purchase p_purchase) {
        this.purchase = p_purchase;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product p_product) {
        this.product = p_product;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer p_quantity) {
        this.quantity = p_quantity;
    }

    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(BigDecimal p_totalPrice) {
        this.totalPrice = p_totalPrice;
    }
}
