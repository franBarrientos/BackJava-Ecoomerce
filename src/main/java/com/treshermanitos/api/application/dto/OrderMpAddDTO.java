package com.treshermanitos.api.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderMpAddDTO {
    private Long idPurchase;
    private List<PurchaseProductAddDTO> products;
}
