package com.treshermanitos.api.application.service;

import com.treshermanitos.api.application.dto.PurchaseAddDTO;
import com.treshermanitos.api.application.dto.PurchaseDTO;
import com.treshermanitos.api.application.exceptions.NotFoundException;
import com.treshermanitos.api.application.mapper.PurchaseAddDtoMapper;
import com.treshermanitos.api.application.mapper.PurchaseDtoMapper;
import com.treshermanitos.api.application.repository.*;
import com.treshermanitos.api.domain.Purchase;
import com.treshermanitos.api.domain.PurchaseProduct;
import com.treshermanitos.api.application.dto.SalesStadistics;
import com.treshermanitos.api.infrastructure.db.springdata.repository.PurchaseProductDboRepository;
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
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PurchaseDtoMapper purchaseDtoMapper;
    private final PurchaseAddDtoMapper purchaseAddDtoMapper;

    public Page<PurchaseDTO> getAllPurchases(Pageable pageable) {
        return this.purchaseRepository.findAll(pageable)
                .map(this.purchaseDtoMapper::toDto);
    }

    public PurchaseDTO getById(Long id) {
        return this.purchaseDtoMapper
                .toDto(this.purchaseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("purchase " + id + " not found")));
    }

    public Page<PurchaseDTO> search(Integer dni, String firstName, String lastName, Pageable pageable) {
        return this.purchaseRepository.search(dni, firstName, lastName, pageable)
                .map(this.purchaseDtoMapper::toDto);
    }

    @Transactional
    public PurchaseDTO createOne(PurchaseAddDTO purchase) {
        Purchase purchaseToSave = this.purchaseAddDtoMapper
                .purchaseAddDTOtoDomain(purchase);

        List<PurchaseProduct> products = purchaseToSave.getPurchaseProducts();

        Purchase purchaseSaved = this.purchaseRepository.save(purchaseToSave);

        products.forEach(pp -> pp.setPurchase(purchaseSaved));

        List<PurchaseProduct> productsSaved =
                this.purchaseProductRepository.saveAll(products);

        purchaseSaved.setPurchaseProducts(productsSaved);

        return this.purchaseDtoMapper
                .toDto(this.purchaseRepository.save(purchaseSaved));
    }

    public PurchaseDTO updateById(Long id, PurchaseAddDTO purchaseToUpdate) {

        Purchase purchaseUpdated =  this.purchaseAddDtoMapper
                .purchaseAddDTOtoDomainWithPurchaseOptional(purchaseToUpdate);

        Purchase purchase = this.purchaseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("purchase " + id + " not found"));

        if (purchaseUpdated.getPayment() != null) {
            purchase.setPayment(purchaseUpdated.getPayment());
        }
        if (purchaseUpdated.getPurchaseProducts() != null) {
            purchase.setPurchaseProducts(purchaseUpdated.getPurchaseProducts());
        }
        if (purchaseUpdated.getState() != null) {
            purchase.setState(purchaseUpdated.getState());
        }

        return this.purchaseDtoMapper.toDto
                (this.purchaseRepository.save(purchase));
    }


    public SalesStadistics getStadistics() {
        return SalesStadistics.builder()
                .stadisticsProducts(this.productRepository.get5mostSales())
                .stadisticsCategories(this.categoryRepository.get5mostSales())
                .stadisticsLast10days(this.purchaseRepository.getLast10DaysStadistics())
                .build();
    }


    public PurchaseDTO deleteById(long id) {
        Purchase purchase = this.purchaseRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("purchase " + id + " not found"));

        return this.purchaseDtoMapper.toDto
                (this.purchaseRepository.delete(purchase));
    }
}
