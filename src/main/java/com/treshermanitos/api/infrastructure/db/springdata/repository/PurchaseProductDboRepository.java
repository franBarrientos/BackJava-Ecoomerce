package com.treshermanitos.api.infrastructure.db.springdata.repository;

import com.treshermanitos.api.domain.PurchaseProduct;
import com.treshermanitos.api.infrastructure.db.springdata.mapper.PurchaseProductEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseProductDboRepository {
    private final SpringDataPurchaseProductRepository purchaseProductRepository;
    private final PurchaseProductEntityMapper purchaseProductEntityMapper;

    public List<PurchaseProduct> saveAll(List<PurchaseProduct> purchaseProducts){
        return this.purchaseProductRepository.saveAll(purchaseProducts
                .stream()
                .map(purchaseProductEntityMapper::toEntity)
                        .collect(Collectors.toList()))
                .stream()
                .map(purchaseProductEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

}
