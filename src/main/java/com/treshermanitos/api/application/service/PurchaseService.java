package com.treshermanitos.api.application.service;

import com.treshermanitos.api.application.exceptions.NotFoundException;
import com.treshermanitos.api.application.repository.CategoryRepository;
import com.treshermanitos.api.application.repository.PurchaseRepository;
import com.treshermanitos.api.application.repository.UserRepository;
import com.treshermanitos.api.domain.Purchase;
import com.treshermanitos.api.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public Page<Purchase> getAllPurchases(Pageable pageable){
        return this.purchaseRepository.findAll(pageable);
    }

    public Purchase getUserIsActive(Long id) {
        return this.purchaseRepository
                .findById(id)
                .orElseThrow(()->new NotFoundException("purchase "+id+" not found"));
    }


}
