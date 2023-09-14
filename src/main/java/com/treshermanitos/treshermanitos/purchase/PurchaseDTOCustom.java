package com.treshermanitos.treshermanitos.purchase;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseDTOCustom {
    private Long purchase_id;
    private PaymentMethod payment;
    private Long customer_id;
    private String dni;
    private String addres;
    private Long user_id;
    private String firstName;
    private  String lastName;
}
