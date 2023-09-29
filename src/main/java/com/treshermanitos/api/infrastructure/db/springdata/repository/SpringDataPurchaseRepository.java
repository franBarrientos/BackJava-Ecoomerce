package com.treshermanitos.api.infrastructure.db.springdata.repository;

import com.treshermanitos.api.infrastructure.db.springdata.entities.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
}
