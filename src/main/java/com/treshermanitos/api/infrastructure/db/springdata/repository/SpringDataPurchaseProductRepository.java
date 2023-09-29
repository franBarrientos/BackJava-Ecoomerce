package com.treshermanitos.api.infrastructure.db.springdata.repository;

import com.treshermanitos.api.infrastructure.db.springdata.entities.PurchaseProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPurchaseProductRepository extends JpaRepository<PurchaseProductEntity, Long> {
}
