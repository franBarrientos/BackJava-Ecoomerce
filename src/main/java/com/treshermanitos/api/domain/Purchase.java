package com.treshermanitos.api.domain;

import java.util.Date;
import java.util.List;


public class Purchase {
    private Long id;

    private PaymentMethod payment;

    private Customer customer;

    private String state;

    private Date createdAt;
    private Date updatedAt;

    private List<PurchaseProduct> purchaseProducts;

    public Purchase(Long id, PaymentMethod payment, Customer customer,
                    String state, Date createdAt, Date updatedAt,
                    List<PurchaseProduct> purchaseProducts) {
        this.id = id;
        this.payment = payment;
        this.customer = customer;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.purchaseProducts = purchaseProducts;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long p_id) {
        this.id = p_id;
    }

    public PaymentMethod getPayment() {
        return this.payment;
    }

    public void setPayment(PaymentMethod p_payment) {
        this.payment = p_payment;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer p_customer) {
        this.customer = p_customer;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String p_state) {
        this.state = p_state;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date p_createdAt) {
        this.createdAt = p_createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date p_updatedAt) {
        this.updatedAt = p_updatedAt;
    }

    public List<PurchaseProduct> getPurchaseProducts() {
        return this.purchaseProducts;
    }

    public void setPurchaseProducts(List<PurchaseProduct> p_purchaseProducts) {
        this.purchaseProducts = p_purchaseProducts;
    }
}
