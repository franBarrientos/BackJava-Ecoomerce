package com.treshermanitos.treshermanitos.purchase;

import com.treshermanitos.treshermanitos.customer.Customer;
import com.treshermanitos.treshermanitos.customer.CustomerDTO;
import com.treshermanitos.treshermanitos.product.ProductDTO;
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

    private CustomerDTO customer;

/*
    private List<ProductDTO> products;
*/

    private String state;

    private Date createdAt;
}
