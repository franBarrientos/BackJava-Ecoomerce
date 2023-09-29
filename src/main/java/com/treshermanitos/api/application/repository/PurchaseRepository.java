package com.treshermanitos.api.application.repository;

import com.treshermanitos.api.domain.Customer;
import com.treshermanitos.api.domain.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PurchaseRepository {
    Page<Purchase> findAll(Pageable pageable);

    Optional<Purchase> findById(Long id);

    Purchase save(Purchase purchase);
}
