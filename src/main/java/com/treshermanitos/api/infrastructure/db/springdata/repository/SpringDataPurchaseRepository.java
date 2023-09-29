package com.treshermanitos.api.infrastructure.db.springdata.repository;

import com.treshermanitos.api.domain.Purchase;
import com.treshermanitos.api.infrastructure.db.springdata.entities.PurchaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpringDataPurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

    @Query("SELECT p FROM Purchase p " +
            "WHERE p.customer.dni = :dni " +
            "OR p.customer.user.firstName LIKE %:firstName% " +
            "OR p.customer.user.lastName LIKE %:lastName%")
    Page<PurchaseEntity> search(Integer dni, String firstName, String lastName ,Pageable pageable);
}
