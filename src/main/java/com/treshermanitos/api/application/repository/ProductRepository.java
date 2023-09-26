package com.treshermanitos.api.application.repository;

import com.treshermanitos.api.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductRepository {
    Page<Product> findAllByHasStockIsTrue(Pageable pageable);

    Optional<Product> findByIdAndHasStockIsTrue(Long id);

    Product save(Product product);
}
