package com.treshermanitos.api.purchase.dto;

import com.treshermanitos.api.purchase.Purchase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseDTOMapper implements Function<Purchase, PurchaseDTO> {

    @Override
    public PurchaseDTO apply(Purchase p) {
        return PurchaseDTO.builder()
                .id(p.getId())
                .payment(p.getPayment())
                .customerId(p.getCustomer().getId())
                .dni(p.getCustomer().getDni())
                .addres(p.getCustomer().getAddres())
                .phone(p.getCustomer().getPhone())
                .lastName(p.getCustomer().getUserEntity().getLastName())
                .firstName(p.getCustomer().getUserEntity().getFirstName())
                .products(p.getPurchaseProducts()
                        .stream()
                        .map(ProductSold::new)
                        .collect(Collectors.toList())
                )
                .state(p.getState())
                .createdAt(p.getCreatedAt())
                .build();
    }
}
