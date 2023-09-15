package com.treshermanitos.treshermanitos.purchase.PurchaseProjection;
import com.treshermanitos.treshermanitos.purchase.PaymentMethod;

import java.util.List;

public interface PurchaseProjection {
    Long getId();

    PaymentMethod getPayment();

    CustomerProjection getCustomer();

    List<ProductProjection> getProducts();

}

