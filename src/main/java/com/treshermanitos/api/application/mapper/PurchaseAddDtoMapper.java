package com.treshermanitos.api.application.mapper;

import com.treshermanitos.api.application.exceptions.NotFoundException;
import com.treshermanitos.api.application.repository.CustomerRepository;
import com.treshermanitos.api.application.repository.ProductRepository;
import com.treshermanitos.api.domain.Product;
import com.treshermanitos.api.domain.Purchase;
import com.treshermanitos.api.domain.PurchaseProduct;
import com.treshermanitos.api.application.dto.PurchaseAddDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PurchaseAddDtoMapper {
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public Purchase purchaseAddDTOtoDomain(PurchaseAddDTO purchaseAddDTO) {
        return Purchase.builder()
                .payment(purchaseAddDTO.getPayment())
                .customer((this.customerRepository
                        .findById(purchaseAddDTO.getCustomerId())
                        .orElseThrow(() -> new NotFoundException("customer "
                                + purchaseAddDTO.getCustomerId() +
                                " not found"))))
                .purchaseProducts(purchaseAddDTO
                        .getPurchaseProducts()
                        .stream()
                        .map(p -> {
                            Product product = this.productRepository
                                    .findByIdAndHasStockIsTrue(p.getProductId())
                                    .orElseThrow(() -> new NotFoundException("Product " +
                                            p.getProductId() + " not found"));

                            return PurchaseProduct.builder()
                                    .product(product)
                                    .quantity(p.getQuantity())
                                    .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())))
                                    .build();
                        }).collect(Collectors.toList()))
                .build();
    }

    public Purchase purchaseAddDTOtoDomainWithPurchaseOptional(PurchaseAddDTO purchaseAddDTO) {
        return Purchase.builder()
                .payment(purchaseAddDTO.getPayment() != null ? purchaseAddDTO.getPayment()
                        : null)
                .customer(
                        purchaseAddDTO.getCustomerId() != null ?
                                (this.customerRepository
                                        .findById(purchaseAddDTO.getCustomerId())
                                        .orElseThrow(() -> new NotFoundException("customer "
                                                + purchaseAddDTO.getCustomerId() +
                                                " not found")))
                                : null)
                .purchaseProducts(
                        purchaseAddDTO.getPurchaseProducts() != null ?
                                purchaseAddDTO
                                        .getPurchaseProducts()
                                        .stream()
                                        .map(p -> {
                                            Product product = this.productRepository
                                                    .findByIdAndHasStockIsTrue(p.getProductId())
                                                    .orElseThrow(() -> new NotFoundException("Product " +
                                                            p.getProductId() + " not found"));

                                            return PurchaseProduct.builder()
                                                    .product(product)
                                                    .quantity(p.getQuantity())
                                                    .totalPrice(product.getPrice()
                                                            .multiply(BigDecimal.valueOf(p.getQuantity())))
                                                    .build();
                                        }).collect(Collectors.toList())
                                : null)
                .build();
    }

}
