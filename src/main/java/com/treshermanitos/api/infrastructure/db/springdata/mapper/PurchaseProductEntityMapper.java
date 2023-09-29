package com.treshermanitos.api.infrastructure.db.springdata.mapper;

import com.treshermanitos.api.domain.PurchaseProduct;
import com.treshermanitos.api.infrastructure.db.springdata.entities.PurchaseProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface PurchaseProductEntityMapper {
    @Mapping(target = "purchase.purchaseProducts", ignore = true)
    PurchaseProduct toDomain(PurchaseProductEntity entity);


    @Mapping(target = "purchase.purchaseProducts", ignore = true)
    PurchaseProductEntity toEntity(PurchaseProduct domain);
}
