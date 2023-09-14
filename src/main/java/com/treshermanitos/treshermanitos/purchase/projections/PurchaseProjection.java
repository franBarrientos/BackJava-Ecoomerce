package com.treshermanitos.treshermanitos.purchase.projections;
import com.treshermanitos.treshermanitos.product.Product;
import com.treshermanitos.treshermanitos.purchase.PaymentMethod;

import java.util.List;

public interface PurchaseProjection {
    Long getId();

    PaymentMethod getPayment();

    CustomerProjection getCustomer();

    List<ProductProjection> getProducts();

}

