package com.treshermanitos.treshermanitos.purchase.PurchaseDto;

import com.treshermanitos.treshermanitos.product.Product;
import com.treshermanitos.treshermanitos.purchase.PaymentMethod;
import com.treshermanitos.treshermanitos.purchase.projections.PurchaseProjectionClassI;
import com.treshermanitos.treshermanitos.purchase.projections.PurchaseProjectionFasterString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {

    private Long id;

    private PaymentMethod payment;

    private Long customerId;

    private Integer dni;

    private String addres;

    private String phone;

    private String firstName;

    private String lastName;

    private List<ProductProjection> products;

    private String state;

    private Date createdAt;

    public PurchaseDTO(
            PurchaseProjectionFasterString p
    ) {
        this.setId(p.getId());
        this.setPayment(p.getPayment());
        this.setCustomerId(p.getCustomerId());
        this.setDni(p.getDni());
        this.setAddres(p.getAddres());
        this.setFirstName(p.getFirstName());
        this.setLastName(p.getLastName());

        if (p.getProducts() != null) {
            List<ProductProjection> productsNewArray = new ArrayList<ProductProjection>();
            String[] productString = p.getProducts().split(", ");
            for (String productEntry : productString) {
                String[] parts = productEntry.split(":");
                Long productId = Long.parseLong(parts[0]);
                String productName = parts[1];
                BigDecimal productPrice = new BigDecimal(parts[2]);
                ProductProjection product = new ProductProjection(productId, productName, productPrice);
                //agregar el price a la concat
                productsNewArray.add(product);
            }
            this.setProducts(productsNewArray);
        } else {
            this.setProducts(null);
        }

        this.setState(state);
        this.setCreatedAt(createdAt);
    }

    public PurchaseDTO(PurchaseProjectionClassI p) {
        this.setId(p.getId());
        this.setPayment(p.getPayment());
        this.setCustomerId(p.getCustomerId());
        this.setDni(p.getDni());
        this.setAddres(p.getAddres());
        this.setFirstName(p.getFirstName());
        this.setLastName(p.getLastName());
        this.setProducts(null);
        this.setState(p.getState());
        this.setCreatedAt(p.getCreatedAt());
    }

    public PurchaseDTO(
            Long id,

            PaymentMethod payment,

            Long customerId,

            Integer dni,

            String addres,

            String phone,

            String firstName,

            String lastName,

            String state,

            Date createdAt
    ) {
        this.setId(id);
        this.setPayment(payment);
        this.setCustomerId(customerId);
        this.setDni(dni);
        this.setAddres(addres);
        this.setPhone(phone);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setProducts(null);
        this.setState(state);
        this.setCreatedAt(createdAt);
    }

    public PurchaseDTO addProducts(List<ProductProjection> products){
        this.setProducts(products);
        return this;
    }








}




