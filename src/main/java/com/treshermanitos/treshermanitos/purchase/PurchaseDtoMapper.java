package com.treshermanitos.treshermanitos.purchase;

import com.treshermanitos.treshermanitos.customer.CustomerDTO;
import com.treshermanitos.treshermanitos.product.ProductDTO;
import com.treshermanitos.treshermanitos.product.ProductoDtoMapper;
import com.treshermanitos.treshermanitos.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseDtoMapper implements Function<Purchase, PurchaseDTO> {

    private final ProductoDtoMapper productoDtoMapper;

    @Override
    public PurchaseDTO apply(Purchase purchase) {
        return PurchaseDTO.builder()
                .id(purchase.getId())
                .payment(purchase.getPayment())
                .customer(CustomerDTO.builder()
                        .id(purchase.getCustomer().getId())
                        .addres(purchase.getCustomer().getAddres())
                        .dni(purchase.getCustomer().getDni())
                        .phone(purchase.getCustomer().getPhone())
                        .user(UserDTO.builder()
                                .id(purchase.getCustomer().getUser().getId())
                                .firstName(purchase.getCustomer().getUser().getFirstName())
                                .lastName(purchase.getCustomer().getUser().getLastName())
                                .email(purchase.getCustomer().getUser().getEmail())
                                .city(purchase.getCustomer().getUser().getCity())
                                .build())
                        .build())
                /*.products(purchase.getProducts()
                        .stream().
                        map(this.productoDtoMapper::apply)
                        .collect(Collectors.toList())
                )*/
                .state(purchase.getState())
                .createdAt(purchase.getCreatedAt())
                .build();
    }
}
