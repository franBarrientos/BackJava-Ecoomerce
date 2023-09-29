package com.treshermanitos.api.application.service;

import com.treshermanitos.api.application.exceptions.NotFoundException;
import com.treshermanitos.api.application.repository.*;
import com.treshermanitos.api.domain.Purchase;
import com.treshermanitos.api.domain.PurchaseProduct;
import com.treshermanitos.api.domain.User;
import com.treshermanitos.api.infrastructure.db.springdata.repository.PurchaseProductDboRepository;
import com.treshermanitos.api.infrastructure.db.springdata.repository.SpringDataPurchaseProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseProductDboRepository purchaseProductRepository;

    public Page<Purchase> getAllPurchases(Pageable pageable) {
        return this.purchaseRepository.findAll(pageable);
    }

    public Purchase getById(Long id) {
        return this.purchaseRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("purchase " + id + " not found"));
    }

    public Page<Purchase> search(Integer dni, String firstName, String lastName, Pageable pageable) {
        return this.purchaseRepository
                .search(dni, firstName, lastName, pageable);
    }

    @Transactional
    public Purchase createOne(Purchase purchase) {
        List<PurchaseProduct> products = purchase.getPurchaseProducts();
        Purchase purchaseSaved = this.purchaseRepository.save(purchase);
        products.forEach(pp -> pp.setPurchase(purchaseSaved));
        List<PurchaseProduct> productsSaved =
                this.purchaseProductRepository.saveAll(products);
        purchaseSaved.setPurchaseProducts(productsSaved);
        return this.purchaseRepository.save(purchaseSaved);
    }


}
