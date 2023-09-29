package com.treshermanitos.api.infrastructure.db.springdata.repository;

import com.treshermanitos.api.application.repository.PurchaseRepository;
import com.treshermanitos.api.domain.Purchase;
import com.treshermanitos.api.infrastructure.db.springdata.entities.PurchaseEntity;
import com.treshermanitos.api.infrastructure.db.springdata.mapper.PurchaseEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseDboRepository implements PurchaseRepository {

    private final SpringDataPurchaseRepository purchaseRepository;
    private final PurchaseEntityMapper purchaseEntityMapper;


    @Override
    public Page<Purchase> findAll(Pageable pageable) {
        return this.purchaseRepository.findAll(pageable)
                .map(purchaseEntityMapper::toDomain);
    }

    @Override
    public Optional<Purchase> findById(Long id) {
        Optional<PurchaseEntity> purchaseEntity = this.purchaseRepository.findById(id);
        return purchaseEntity.isPresent()?
                Optional.of(
                        this.purchaseEntityMapper.toDomain(purchaseEntity.get()))
                :
                Optional.empty();

    }

    @Override
    public Purchase save(Purchase purchase) {
        return this.purchaseEntityMapper
                .toDomain(this.purchaseRepository
                        .save(this.purchaseEntityMapper.toEntity(purchase)));
    }
}
