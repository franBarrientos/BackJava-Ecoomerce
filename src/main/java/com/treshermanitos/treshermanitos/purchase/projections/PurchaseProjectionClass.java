package com.treshermanitos.treshermanitos.purchase.projections;

import com.treshermanitos.treshermanitos.product.Product;
import com.treshermanitos.treshermanitos.purchase.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PurchaseProjectionClass {
    private Long id;
    private PaymentMethod payment;
    private Long customerId;
    private String addres;
    private String dni;
    private Long userId;
    private String firstName;
    private String lastName;
    private List<ProductProjectionClass> products;

    public PurchaseProjectionClass(
            Long id, PaymentMethod payment, Long customerId, String dni,
            String addres, Long userId, String firstName, String lastName, String products
    ) {
        this.setId(id);
        this.setPayment(payment);
        this.setCustomerId(customerId);
        this.setDni(dni);
        this.setAddres(addres);
        this.setUserId(userId);
        this.setFirstName(firstName);
        this.setLastName(lastName);

        if (products != null) {
            List<ProductProjectionClass> productsNewArray = new ArrayList<ProductProjectionClass>();
            String[] productString = products.split(", ");
            for (String productEntry : productString) {
                String[] parts = productEntry.split(":");
                Long productId = Long.parseLong(parts[0]);
                String productName = parts[1];

                ProductProjectionClass product = new ProductProjectionClass(productId, productName);
                productsNewArray.add(product);
            }
            this.setProducts(productsNewArray);
        } else {
            this.setProducts(null);
        }


    }

    public PurchaseProjectionClass(
            Long id, PaymentMethod payment, Long customerId, String dni,
            String addres, Long userId, String firstName, String lastName, List<ProductProjectionClass> products
    ) {
        this.setId(id);
        this.setPayment(payment);
        this.setCustomerId(customerId);
        this.setDni(dni);
        this.setAddres(addres);
        this.setUserId(userId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setProducts(products);
    }

    public Long getId() {
        return this.id;
    }

    public PaymentMethod getPayment() {
        return this.payment;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public String getDni() {
        return this.dni;
    }

    public String getAddres() {
        return this.addres;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public List<ProductProjectionClass> getProducts() {
        return this.products;
    }
}
