package com.treshermanitos.api.infrastructure.db.springdata.mapper;

import com.treshermanitos.api.domain.Purchase;
import com.treshermanitos.api.domain.PurchaseProduct;
import com.treshermanitos.api.infrastructure.db.springdata.entities.PurchaseEntity;
import com.treshermanitos.api.infrastructure.db.springdata.entities.PurchaseProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerEntityMapper.class, ProductEntityMapper.class})
public interface PurchaseEntityMapper {
    Purchase toDomain(PurchaseEntity purchaseEntity);

    @Mapping(target = "purchase", ignore = true)
    PurchaseProduct purchaseProductEntityToPurchaseProduct(PurchaseProductEntity purchaseProductEntity);

    PurchaseEntity toEntity(Purchase purchase);

    @Mapping(target = "purchase", ignore = true)
    PurchaseProductEntity purchaseProductToPurchaseProductEntity(PurchaseProduct purchaseProduct);
}
