package com.treshermanitos.treshermanitos.purchase.projections;

import com.treshermanitos.treshermanitos.purchase.PaymentMethod;

import java.util.Date;
import java.util.List;

public interface PurchaseProjectionFasterString {
    Long getId();
    PaymentMethod getPayment();

    Long getCustomerId();

    Integer getDni();

    String getAddres();

    Long getUserId();

    String getFirstName();
    String getLastName();

    String getProducts();

    String getState();

    Date getCreatedAt();
}
