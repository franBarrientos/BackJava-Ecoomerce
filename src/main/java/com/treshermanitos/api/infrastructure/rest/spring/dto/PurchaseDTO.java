package com.treshermanitos.api.infrastructure.rest.spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.treshermanitos.api.domain.PaymentMethod;
import com.treshermanitos.api.domain.PurchaseProduct;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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

    private String state;

    private Date createdAt;

    private Date updatedAt;

    @JsonIgnoreProperties({"purchase"})
    private List<PurchaseProductDTO> purchaseProducts;

    private BigDecimal totalSale;
}
