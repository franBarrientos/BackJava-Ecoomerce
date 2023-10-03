package com.treshermanitos.api.application.mapper;

import com.treshermanitos.api.domain.Purchase;
import com.treshermanitos.api.application.dto.PurchaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", uses = CustomerDtoMapper.class)
public interface PurchaseDtoMapper {
    @Mapping(target = "totalSale", expression = "java(calculateTotalSale(purchase))")
    PurchaseDTO toDto(Purchase purchase);

    default BigDecimal calculateTotalSale(Purchase purchase) {
        return purchase.getPurchaseProducts()
                .stream()
                .map(pp -> pp.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    };

    Purchase toDomain(PurchaseDTO purchaseDTO);

}
