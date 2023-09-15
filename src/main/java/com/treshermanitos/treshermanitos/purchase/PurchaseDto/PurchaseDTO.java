package com.treshermanitos.treshermanitos.purchase.PurchaseDto;

import com.treshermanitos.treshermanitos.customer.Customer;
import com.treshermanitos.treshermanitos.customer.CustomerDTO;
import com.treshermanitos.treshermanitos.product.ProductDTO;
import com.treshermanitos.treshermanitos.purchase.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
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

    private PaymentMethod payment;

    private Long customerId;

    private Integer dni;

    private String addres;

    private String phone;

    private String firstName;

    private  String lastName;

    private List<ProductProjection> products;

    private String state;

    private Date createdAt;



}




