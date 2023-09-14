package com.treshermanitos.treshermanitos.purchase.projections;

import com.treshermanitos.treshermanitos.product.Product;
import com.treshermanitos.treshermanitos.purchase.PaymentMethod;

import java.util.List;

public interface PurchaseProjectionFaster {
    Long getId();
    PaymentMethod getPayment();

    Long getCustomerId();

    String getDni();

    String getAddres();

    Long getUserId();

    String getFirstName();
    String getLastName();

    List<ProductProjection> getProducts();
}
