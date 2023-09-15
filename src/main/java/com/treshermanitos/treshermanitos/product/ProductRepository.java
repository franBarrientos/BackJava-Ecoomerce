package com.treshermanitos.treshermanitos.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> getAllByHasStockIsTrue(Pageable pageable);

    Optional<Product> getByIdAndHasStockIsTrue(Long id);
    
}
