package com.treshermanitos.api.purchase.dto;

import com.treshermanitos.api.purchase.PaymentMethod;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {

    private Long id;

    @NotNull
    private PaymentMethod payment;

    private Long customerId;

    private Integer dni;

    private String addres;

    private String phone;

    private String firstName;

    private String lastName;

    @NotNull
    private List<ProductSold> products;

    private String state;

    private Date createdAt;



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

    public PurchaseDTO addProducts(List<ProductSold> products){
        this.setProducts(products);
        return this;
    }








}




