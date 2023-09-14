package com.treshermanitos.treshermanitos.purchase.projections;

import com.treshermanitos.treshermanitos.purchase.PaymentMethod;

import java.util.List;

public interface PurchaseProjectionClassI {
    Long getId();

    PaymentMethod getPayment();

    Long getCustomerId();

    String getAddres();

    String getDni();

    Long getUserId();

    String getFirstName();

    String getLastName();

    List<ProductProjectionClass> getProducts();
}
