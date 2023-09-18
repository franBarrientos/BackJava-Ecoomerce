package com.treshermanitos.treshermanitos.purchase.projections;

import com.treshermanitos.treshermanitos.purchase.PaymentMethod;
import com.treshermanitos.treshermanitos.purchase.PurchaseDto.ProductProjection;

import java.util.Date;
import java.util.List;

public interface PurchaseProjectionClassI {
    Long getId();

    PaymentMethod getPayment();

    Long getCustomerId();

    String getAddres();

    Integer getDni();

    Long getUserId();

    String getFirstName();

    String getLastName();

    List<ProductProjection> getProducts();

    String getState();

    Date getCreatedAt();
}
