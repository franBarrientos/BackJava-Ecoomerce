package com.treshermanitos.treshermanitos.purchase.dto;

import com.treshermanitos.treshermanitos.purchase.Purchase;
import com.treshermanitos.treshermanitos.purchase.PurchaseRepository;
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
                .lastName(p.getCustomer().getUser().getLastName())
                .firstName(p.getCustomer().getUser().getFirstName())
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
