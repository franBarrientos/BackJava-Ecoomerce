package com.treshermanitos.api.infrastructure.db.springdata.repository;

import com.treshermanitos.api.application.repository.ProductRepository;
import com.treshermanitos.api.domain.Product;
import com.treshermanitos.api.infrastructure.db.springdata.entities.ProductEntity;
import com.treshermanitos.api.infrastructure.db.springdata.mapper.ProductEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDboRepository implements ProductRepository {

    private final SpringDataProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;

    @Override
    public Page<Product> findAllByHasStockIsTrue(Pageable pageable) {
        Page<Product> tets = this.productRepository.findAllByHasStockIsTrue(pageable)
                .map(productEntityMapper::toDomain);
        return tets;
    }

    @Override
    public Optional<Product> findByIdAndHasStockIsTrue(Long id) {
        Optional<ProductEntity> productEntity = this.productRepository.findByIdAndHasStockIsTrue(id);
        return productEntity.isPresent()?
                Optional.of(
                        this.productEntityMapper.toDomain(productEntity.get()))
                :
                Optional.empty();

    }

    @Override
    public Product save(Product product) {
        return this.productEntityMapper.toDomain(
                this.productRepository.save(this.productEntityMapper.toEntity(product))
        );
    }
}
