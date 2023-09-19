package com.treshermanitos.domain;

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
}
