package com.treshermanitos.treshermanitos.purchase.PurchaseDto;

import com.treshermanitos.treshermanitos.customer.CustomerDTO;
import com.treshermanitos.treshermanitos.product.ProductoDtoMapper;
import com.treshermanitos.treshermanitos.purchase.Purchase;
import com.treshermanitos.treshermanitos.purchase.PurchaseDto.PurchaseDTO;
import com.treshermanitos.treshermanitos.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseDtoMapper implements Function<Purchase, PurchaseDTO> {


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
                .products(p.getProducts()
                        .stream()
                        .map(i->new ProductProjection(i))
                        .collect(Collectors.toList())
                )
                .state(p.getState())
                .createdAt(p.getCreatedAt())
                .build();
    }
}
